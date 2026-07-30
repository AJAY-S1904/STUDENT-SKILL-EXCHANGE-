package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Session(
    val id: String = "",
    val requestId: String = "",
    val teacherId: String = "",
    val teacherName: String = "",
    val teacherProfilePic: String = "",
    val learnerId: String = "",
    val learnerName: String = "",
    val learnerProfilePic: String = "",
    val skill: String = "",
    val date: String = "",
    val time: String = "",
    val durationMinutes: Int = 60,
    val notes: String = "",
    val meetLink: String = "",
    val status: String = SessionStatus.SCHEDULED.name,
    val completedAt: Long = 0L,
    val createdAt: Long = System.currentTimeMillis()
)

enum class SessionStatus {
    SCHEDULED, ONGOING, COMPLETED, CANCELLED
}
