package com.skillswap.ai.data.model

data class McqQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

data class McqTest(
    val skill: String,
    val questions: List<McqQuestion>
)
