package com.example.inscit.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

data class CustomTheme(
    val name: String,
    val primaryAccent: Int, // ARGB
    val background: Int,    // ARGB
    val textColor: Int      // ARGB
) {
    fun getAccentColor() = Color(primaryAccent)
    fun getBgColor() = Color(background)
    fun getTxtColor() = Color(textColor)
}
