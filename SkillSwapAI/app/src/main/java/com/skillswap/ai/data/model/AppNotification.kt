package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
data class AppNotification(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val body: String = "",
    val type: String = NotificationType.GENERAL.name,
    val referenceId: String = "",
    var isRead: Boolean = false,
    @get:JvmName("getReadStatus")
    @set:JvmName("setReadStatus")
    var read: Boolean = false,
    val senderName: String = "",
    val senderProfilePic: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    @com.google.firebase.database.Exclude
    fun getIsActuallyRead(): Boolean = isRead || read
}

enum class NotificationType {
    NEW_REQUEST,
    REQUEST_ACCEPTED,
    REQUEST_REJECTED,
    SESSION_REMINDER,
    RATING_REMINDER,
    CREDIT_EARNED,
    CREDIT_SPENT,
    GENERAL
}
