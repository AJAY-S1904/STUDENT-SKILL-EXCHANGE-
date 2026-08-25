package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Rating(
    val id: String = "",
    val sessionId: String = "", // Legacy or keeping for compatibility
    val meetingId: String = "",
    val exchangeId: String = "",
    val raterId: String = "",
    val raterName: String = "",
    val raterProfilePic: String = "",
    val ratedUserId: String = "",
    val stars: Float = 0f,
    val feedback: String = "",
    val skill: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
