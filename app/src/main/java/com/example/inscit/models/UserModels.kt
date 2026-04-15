package com.example.inscit.models

enum class Lang { EN, HI }
enum class ThemeMode { NEON, NOBLE }

data class UserSettings(
    val language: Lang = Lang.EN,
    val theme: ThemeMode = ThemeMode.NEON
)

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val photoUrl: String? = null
)

data class UserStats(
    val xp: Int = 0,
    val level: Int = 1,
    val quizzesTaken: Int = 0
)

data class QuizProgress(
    val lastScore: Float = 0f,
    val domainScores: Map<String, Float> = emptyMap(),
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList()
)

data class UserNote(
    val content: String = "",
    val drawingData: String = "" // Simplified drawing representation
)

data class UserDocument(
    val profile: UserProfile = UserProfile(),
    val stats: UserStats = UserStats(),
    val quizProgress: QuizProgress = QuizProgress(),
    val settings: UserSettings = UserSettings(),
    val userNotes: Map<String, UserNote> = emptyMap()
)

data class QuizAttempt(
    val attemptId: String = "",
    val score: Float = 0f,
    val xpEarned: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)
