package com.skillswap.ai.data.model

data class AiMatchRequest(
    val teach_skills: List<String>,
    val learning_skills: List<String>,
    val experience: String,
    val rating: Double,
    val availability: List<String>,
    val candidates: List<CandidateProfile>
)

data class CandidateProfile(
    val uid: String,
    val name: String,
    val teach_skills: List<String>,
    val learning_skills: List<String>,
    val verified_skills: List<String>,
    val experience: String,
    val rating: Double,
    val availability: List<String>,
    val college: String,
    val department: String
)

data class AiMatchResponse(
    val recommended_student: RecommendedStudent,
    val match_percentage: Double,
    val compatibility_score: Double,
    val reason: List<String>
)

data class RecommendedStudent(
    val uid: String,
    val name: String,
    val college: String,
    val department: String,
    val teach_skills: List<String>,
    val learning_skills: List<String>,
    val verified_skills: List<String>,
    val experience: String,
    val rating: Double,
    val availability: List<String>
)
