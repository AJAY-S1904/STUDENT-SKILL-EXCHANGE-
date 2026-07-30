package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SkillCredit(
    val id: String = "",
    val userId: String = "",
    val amount: Int = 0,
    val type: String = CreditType.BONUS.name,
    val description: String = "",
    val referenceId: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

enum class CreditType {
    EARNED_TEACHING,
    SPENT_LEARNING,
    BONUS,
    REFUND
}
