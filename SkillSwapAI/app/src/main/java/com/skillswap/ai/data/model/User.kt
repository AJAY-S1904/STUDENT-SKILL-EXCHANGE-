package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val uid: String = "",
    val email: String = "",
    val name: String = "",
    val studentId: String = "",
    val profilePictureUrl: String = "",
    val college: String = "",
    val department: String = "",
    val year: String = "",
    val bio: String = "",
    val teachSkills: List<String> = emptyList(),
    val learnSkills: List<String> = emptyList(),
    val verifiedSkills: List<String> = emptyList(),
    val experienceLevel: String = "Beginner",
    val availability: List<String> = emptyList(),
    val rating: Double = 0.0,
    val ratingCount: Int = 0,
    val skillCredits: Int = 10,
    val fcmToken: String = "",
    val isActive: Boolean = true,
    val isOnline: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
