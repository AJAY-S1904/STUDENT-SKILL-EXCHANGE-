package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.AppNotification
import com.skillswap.ai.data.model.NotificationType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val notifRef = database.getReference("Notifications")

    fun getUserNotifications(userId: String): Flow<List<AppNotification>> = callbackFlow {
        val ref = notifRef.orderByChild("userId").equalTo(userId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<AppNotification>()
                snapshot.children.forEach { child ->
                    child.getValue(AppNotification::class.java)?.let {
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

    suspend fun createNotification(notification: AppNotification): AuthResult<Unit> {
        return try {
            val newRef = notifRef.push()
            newRef.setValue(notification.copy(id = newRef.key ?: "")).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to create notification")
        }
    }

    suspend fun markAsRead(notificationId: String): AuthResult<Unit> {
        return try {
            notifRef.child(notificationId).child("isRead").setValue(true).await()
            notifRef.child(notificationId).child("read").setValue(true).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to mark as read")
        }
    }

    suspend fun markAllAsRead(userId: String): AuthResult<Unit> {
        return try {
            val snapshot = notifRef.orderByChild("userId").equalTo(userId).get().await()
            val updates = mutableMapOf<String, Any>()
            snapshot.children.forEach { child ->
                val notification = child.getValue(AppNotification::class.java)
                if (notification != null && !notification.isRead) {
                    updates["${child.key}/isRead"] = true
                    updates["${child.key}/read"] = true
                }
            }
            if (updates.isNotEmpty()) notifRef.updateChildren(updates).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed")
        }
    }

    suspend fun getUnreadCount(userId: String): Int {
        return try {
            val snapshot = notifRef.orderByChild("userId").equalTo(userId).get().await()
            snapshot.children.count { child ->
                val notification = child.getValue(AppNotification::class.java)
                notification != null && !notification.isRead
            }
        } catch (_: Exception) { 0 }
    }

    suspend fun sendRequestNotification(
        receiverId: String,
        senderName: String,
        senderPic: String,
        requestId: String
    ) {
        createNotification(
            AppNotification(
                userId = receiverId,
                title = "New Exchange Request",
                body = "$senderName wants to exchange skills with you!",
                type = NotificationType.NEW_REQUEST.name,
                referenceId = requestId,
                senderName = senderName,
                senderProfilePic = senderPic
            )
        )
    }

    suspend fun sendAcceptedNotification(
        receiverId: String,
        acceptorName: String,
        acceptorPic: String,
        requestId: String
    ) {
        createNotification(
            AppNotification(
                userId = receiverId,
                title = "Request Accepted! 🎉",
                body = "$acceptorName accepted your skill exchange request!",
                type = NotificationType.REQUEST_ACCEPTED.name,
                referenceId = requestId,
                senderName = acceptorName,
                senderProfilePic = acceptorPic
            )
        )
    }
}
