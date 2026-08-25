package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MeetingRequest(
    val meetingId: String = "",
    val requestId: String = "",
    val learnerId: String = "",
    val learnerName: String = "",
    val learnerProfilePic: String = "",
    val teacherId: String = "",
    val teacherName: String = "",
    val teacherProfilePic: String = "",
    val meetingDate: String = "",
    val meetingTime: String = "",
    val meetingMode: String = "Online", // Online or Offline
    val meetingLocationOrLink: String = "",
    val jitsiRoomName: String = "",
    val notes: String = "",
    val meetingStatus: String = MeetingStatus.PENDING.name,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val completedAt: Long = 0L
)
