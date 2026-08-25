package com.skillswap.ai.data.model

data class AiCareerAnalysis(
    val id: String = "",
    val userId: String = "",
    val careerGoal: String = "",
    val targetSkill: String = "",
    
    // Skill Gap Analysis fields
    val currentSkills: List<String> = emptyList(),
    val overallReadiness: Int = 0,
    val skillGap: Int = 0,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val missingSkills: List<String> = emptyList(),
    val recommendations: String = "",
    
    val createdAt: Long = System.currentTimeMillis()
)
