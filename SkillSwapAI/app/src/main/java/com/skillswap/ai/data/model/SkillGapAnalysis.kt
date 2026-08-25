package com.skillswap.ai.data.model

data class SkillGapAnalysis(
    val id: String = "",
    val userId: String = "",
    val currentSkills: List<String> = emptyList(),
    val desiredSkill: String = "",
    val careerGoal: String = "",
    val gapPercentage: Int = 0,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val missingSkills: List<String> = emptyList(),
    val recommendations: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
