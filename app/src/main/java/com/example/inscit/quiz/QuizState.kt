package com.example.inscit.quiz

sealed class QuizUiState {
    object Loading : QuizUiState()
    
    data class QuizInProgress(
        val currentQuestion: ScienceQuestion,
        val currentIndex: Int,
        val totalQuestions: Int,
        val selectedOptionId: Int? = null,
        val isTransitioning: Boolean = false
    ) : QuizUiState()

    data class Completed(
        val analytics: ScienceAnalytics
    ) : QuizUiState()

    data class Error(val message: String) : QuizUiState()
}
