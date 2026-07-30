package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.CreditType
import com.skillswap.ai.data.model.SkillCredit
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val creditsRef = database.getReference("SkillCredits")
    private val usersRef = database.getReference("Users")

    suspend fun earnCredits(
        userId: String,
        amount: Int,
        description: String,
        sessionId: String = ""
    ): AuthResult<Unit> {
        return try {
            val credit = SkillCredit(
                userId = userId,
                amount = amount,
                type = CreditType.EARNED_TEACHING.name,
                description = description,
                referenceId = sessionId
            )
            val newRef = creditsRef.push()
            newRef.setValue(credit.copy(id = newRef.key ?: "")).await()
            // Update user balance
            val balanceSnap = usersRef.child(userId).child("skillCredits").get().await()
            val current = balanceSnap.getValue(Int::class.java) ?: 10
            usersRef.child(userId).child("skillCredits").setValue(current + amount).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to earn credits")
        }
    }

    suspend fun spendCredits(
        userId: String,
        amount: Int,
        description: String,
        requestId: String = ""
    ): AuthResult<Unit> {
        return try {
            val balanceSnap = usersRef.child(userId).child("skillCredits").get().await()
            val current = balanceSnap.getValue(Int::class.java) ?: 0
            if (current < amount) return AuthResult.Error("Insufficient credits")

            val credit = SkillCredit(
                userId = userId,
                amount = -amount,
                type = CreditType.SPENT_LEARNING.name,
                description = description,
                referenceId = requestId
            )
            val newRef = creditsRef.push()
            newRef.setValue(credit.copy(id = newRef.key ?: "")).await()
            usersRef.child(userId).child("skillCredits").setValue(current - amount).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to spend credits")
        }
    }

    fun getCreditHistory(userId: String): Flow<List<SkillCredit>> = callbackFlow {
        val ref = creditsRef.orderByChild("userId").equalTo(userId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<SkillCredit>()
                snapshot.children.forEach { child ->
                    child.getValue(SkillCredit::class.java)?.let {
                        list.add(it.copy(id = child.key ?: ""))
                    }
                }
                trySend(list.sortedByDescending { it.createdAt })
            }
            override fun onCancelled(error: DatabaseError) { trySend(emptyList()) }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}
