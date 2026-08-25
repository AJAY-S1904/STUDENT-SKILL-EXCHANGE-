package com.skillswap.ai.data.model

data class LearningRoadmap(
    val id: String = "",
    val userId: String = "",
    val analysisId: String = "",
    val careerGoal: String = "",
    val targetSkill: String = "",
    val overallEstimatedWeeks: Int = 0,
    val difficultyLevel: String = "",
    val stages: List<LearningStage> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)

data class LearningStage(
    val title: String = "",
    val estimatedDuration: String = "",
    val description: String = "",
    val highPriorityTopics: List<String> = emptyList(),
    val mediumPriorityTopics: List<String> = emptyList(),
    val advancedTopics: List<String> = emptyList()
)
