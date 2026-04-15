package com.example.inscit.models

import androidx.compose.ui.graphics.Color
import com.example.inscit.Branch

data class TopicDetail(
    val id: String,
    val title: String,
    val paragraphs: List<String>,
    val branch: Branch,
    val accentColor: Color? = null
)

data class TopicCategory(
    val branch: Branch,
    val topics: List<TopicDetail>
)
