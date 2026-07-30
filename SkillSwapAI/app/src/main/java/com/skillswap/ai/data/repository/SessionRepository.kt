package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.Session
import com.skillswap.ai.data.model.SessionStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val sessionsRef = database.getReference("Sessions")

    suspend fun createSession(session: Session): AuthResult<String> {
        return try {
            val newRef = sessionsRef.push()
            val id = newRef.key ?: ""
            newRef.setValue(session.copy(id = id)).await()
            AuthResult.Success(id)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to create session")
        }
    }

    fun getTeacherSessions(teacherId: String): Flow<List<Session>> = callbackFlow {
        val ref = sessionsRef.orderByChild("teacherId").equalTo(teacherId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Session>()
                snapshot.children.forEach { child ->
                    child.getValue(Session::class.java)?.let {
                        list.add(it.copy(id = child.key ?: ""))
                    }
                }
                trySend(list)
            }
            override fun onCancelled(error: DatabaseError) { trySend(emptyList()) }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    fun getLearnerSessions(learnerId: String): Flow<List<Session>> = callbackFlow {
        val ref = sessionsRef.orderByChild("learnerId").equalTo(learnerId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Session>()
                snapshot.children.forEach { child ->
                    child.getValue(Session::class.java)?.let {
                        list.add(it.copy(id = child.key ?: ""))
                    }
                }
                trySend(list)
            }
            override fun onCancelled(error: DatabaseError) { trySend(emptyList()) }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    suspend fun scheduleSession(sessionId: String, date: String, time: String): AuthResult<Unit> {
        return try {
            sessionsRef.child(sessionId).updateChildren(
                mapOf(
                    "date" to date,
                    "time" to time
                )
            ).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to schedule session")
        }
    }

    suspend fun markSessionComplete(sessionId: String): AuthResult<Unit> {
        return try {
            sessionsRef.child(sessionId).updateChildren(
                mapOf(
                    "status" to SessionStatus.COMPLETED.name,
                    "completedAt" to System.currentTimeMillis()
                )
            ).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to mark complete")
        }
    }

    suspend fun updateNotes(sessionId: String, notes: String): AuthResult<Unit> {
        return try {
            sessionsRef.child(sessionId).child("notes").setValue(notes).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update notes")
        }
    }
}
