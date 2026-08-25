package com.skillswap.ai.data.model

data class SkillPortfolio(
    val id: String = "",
    val userId: String = "",
    val verifiedSkills: List<String> = emptyList(),
    val totalSessionsCompleted: Int = 0,
    val averageRating: Double = 0.0,
    val certificates: List<String> = emptyList(), // URLs to certificates
    val timestamp: Long = System.currentTimeMillis()
)
