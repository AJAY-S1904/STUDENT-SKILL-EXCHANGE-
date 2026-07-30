package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ExchangeRequest(
    val id: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val senderProfilePic: String = "",
    val receiverId: String = "",
    val receiverName: String = "",
    val receiverProfilePic: String = "",
    val teachSkill: String = "",
    val learnSkill: String = "",
    val message: String = "",
    val status: String = RequestStatus.PENDING.name,
    val creditCost: Int = 5,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class RequestStatus {
    PENDING, ACCEPTED, COMPLETED, CANCELLED, REJECTED
}

enum class MeetingStatus {
    PENDING, CONFIRMED, RESCHEDULED, REJECTED, COMPLETED
}
