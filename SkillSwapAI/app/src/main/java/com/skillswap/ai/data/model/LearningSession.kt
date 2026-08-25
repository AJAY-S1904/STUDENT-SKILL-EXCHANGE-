package com.skillswap.ai.data.model

enum class LearningSessionStatus {
    SCHEDULED,
    COMPLETED,
    CANCELLED
}

data class LearningSession(
    val id: String = "",
    val parentMeetingId: String = "", // Links to Session.kt or MeetingRequest.kt
    val title: String = "",
    val date: String = "",
    val time: String = "",
    val status: LearningSessionStatus = LearningSessionStatus.SCHEDULED,
    val notes: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
