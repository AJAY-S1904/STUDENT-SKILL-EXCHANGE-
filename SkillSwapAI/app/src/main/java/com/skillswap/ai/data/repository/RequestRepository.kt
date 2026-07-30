package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.RequestStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val requestsRef = database.getReference("ExchangeRequests")

    suspend fun sendRequest(request: ExchangeRequest): AuthResult<String> {
        return try {
            val newRef = requestsRef.push()
            val id = newRef.key ?: ""
            newRef.setValue(request.copy(id = id)).await()
            AuthResult.Success(id)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to send request")
        }
    }

    fun getSentRequests(userId: String): Flow<List<ExchangeRequest>> = callbackFlow {
        val ref = requestsRef.orderByChild("senderId").equalTo(userId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ExchangeRequest>()
                snapshot.children.forEach { child ->
                    child.getValue(ExchangeRequest::class.java)?.let {
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

    fun getReceivedRequests(userId: String): Flow<List<ExchangeRequest>> = callbackFlow {
        val ref = requestsRef.orderByChild("receiverId").equalTo(userId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ExchangeRequest>()
                snapshot.children.forEach { child ->
                    child.getValue(ExchangeRequest::class.java)?.let {
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

    suspend fun updateRequestStatus(
        requestId: String,
        status: RequestStatus
    ): AuthResult<Unit> {
        return try {
            requestsRef.child(requestId).child("status").setValue(status.name).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update status")
        }
    }

    suspend fun updateRequest(
        request: ExchangeRequest
    ): AuthResult<Unit> {
        return try {
            requestsRef.child(request.id).setValue(request).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update request")
        }
    }

    suspend fun getRequest(requestId: String): AuthResult<ExchangeRequest> {
        return try {
            val snapshot = requestsRef.child(requestId).get().await()
            val req = snapshot.getValue(ExchangeRequest::class.java)?.copy(id = requestId)
            if (req != null) AuthResult.Success(req)
            else AuthResult.Error("Request not found")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to get request")
        }
    }
}
