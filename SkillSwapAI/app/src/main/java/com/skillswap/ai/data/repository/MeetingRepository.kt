package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.MeetingRequest
import com.skillswap.ai.data.model.MeetingStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeetingRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val meetingsRef = database.getReference("MeetingRequests")

    suspend fun createMeetingRequest(request: MeetingRequest): AuthResult<String> {
        return try {
            val newRef = meetingsRef.push()
            val id = newRef.key ?: ""
            newRef.setValue(request.copy(meetingId = id)).await()
            AuthResult.Success(id)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to create meeting request")
        }
    }

    fun getMeetingRequestsForUser(userId: String): Flow<List<MeetingRequest>> = callbackFlow {
        // Since we can only order by one child, we fetch all where user is learner OR teacher.
        // Firebase RTDB doesn't support OR queries, so we'll fetch both and combine, or just fetch all meetings and filter.
        // For scalability, we should ideally duplicate or use Cloud Firestore. But since it's RTDB, we'll use two listeners.
        
        val list = mutableSetOf<MeetingRequest>()
        
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                snapshot.children.forEach { child ->
                    child.getValue(MeetingRequest::class.java)?.let {
                        if (it.learnerId == userId || it.teacherId == userId) {
                            list.add(it.copy(meetingId = child.key ?: ""))
                        }
                    }
                }
                trySend(list.toList().sortedByDescending { it.createdAt })
            }
            override fun onCancelled(error: DatabaseError) { trySend(emptyList()) }
        }
        
        // Since we want both sender and receiver, we just listen to the whole node or query by one and filter client side.
        // Let's query where learnerId == userId for now and then add teacherId == userId
        // Actually, for simplicity and since we don't have many records, listening to meetingsRef is okay,
        // but better to just attach listener and filter.
        meetingsRef.addValueEventListener(listener)
        awaitClose { meetingsRef.removeEventListener(listener) }
    }

    suspend fun updateMeetingStatus(
        meetingId: String,
        status: MeetingStatus
    ): AuthResult<Unit> {
        return try {
            meetingsRef.child(meetingId).child("meetingStatus").setValue(status.name).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update status")
        }
    }

    suspend fun updateJitsiRoomName(meetingId: String, roomName: String): AuthResult<Unit> {
        return try {
            meetingsRef.child(meetingId).child("jitsiRoomName").setValue(roomName).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update Jitsi room name")
        }
    }

    suspend fun completeMeeting(meetingId: String): AuthResult<Unit> {
        return try {
            val updates = mapOf(
                "meetingStatus" to MeetingStatus.COMPLETED.name,
                "completedAt" to com.google.firebase.database.ServerValue.TIMESTAMP
            )
            meetingsRef.child(meetingId).updateChildren(updates).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to complete meeting")
        }
    }
}
