package com.skillswap.ai.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Skill(
    val id: String = "",
    val name: String = "",
    val category: String = "",           // e.g. "Programming", "Design", "Music"
    val description: String = "",
    val iconUrl: String = "",
    val popularityCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
