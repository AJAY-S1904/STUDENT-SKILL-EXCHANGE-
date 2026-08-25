package com.skillswap.ai.data.model

enum class VerificationStatus {
    PENDING,
    VERIFIED,
    REJECTED
}

enum class VerificationMethod {
    CERTIFICATE,
    MANUAL,
    MCQ_TEST
}

data class SkillVerification(
    val id: String = "",
    val userId: String = "",
    val skillName: String = "",
    val status: VerificationStatus = VerificationStatus.PENDING,
    val method: VerificationMethod = VerificationMethod.MANUAL,
    val certificateUrl: String? = null,
    val mcqScore: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
)
