package com.example.inscit.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscit.*
import com.example.inscit.models.Lang
import com.example.inscit.quiz.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ScienceQuizScreen(
    lang: Lang,
    accent: Color,
    onFinish: (xpEarned: Int, score: Int, strengths: List<String>, weaknesses: List<String>) -> Unit,
    viewModel: QuizViewModel = viewModel<QuizViewModel>()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.startQuiz(lang)
        viewModel.events.collectLatest { event ->
            when (event) {
                is QuizEvent.TriggerVibration -> triggerVibration(context, event.type)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(DeepSpace)) {
        when (val currentState = state) {
            is QuizUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = accent
                )
            }
            is QuizUiState.QuizInProgress -> {
                QuizContent(currentState, lang, accent, viewModel)
            }
            is QuizUiState.Completed -> {
                ScienceResultScreen(
                    analytics = currentState.analytics,
                    lang = lang,
                    accent = accent,
                    onRetry = viewModel::retry,
                    onFinish = {
                        onFinish(
                            viewModel.getFinalXp(),
                            currentState.analytics.overallScore,
                            currentState.analytics.strengthsEn, // Using English for saving
                            currentState.analytics.weaknessesEn
                        )
                    }
                )
            }
            is QuizUiState.Error -> {
                Text(
                    text = currentState.message,
                    color = PowerRed,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
private fun QuizContent(
    state: QuizUiState.QuizInProgress,
    lang: Lang,
    accent: Color,
    viewModel: QuizViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val progress = (state.currentIndex + 1).toFloat() / state.totalQuestions
        
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = accent,
            trackColor = accent.copy(alpha = 0.2f)
        )
        
        Spacer(Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (lang == Lang.EN) "QUESTION ${state.currentIndex + 1}" else "प्रश्न ${state.currentIndex + 1}",
                color = accent,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            
            Text(
                text = "PENDING XP: ${viewModel.pendingXp}",
                color = GhostWhite.copy(alpha = 0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = state.currentQuestion.text,
            fontSize = 26.sp,
            color = GhostWhite,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            state.currentQuestion.options.forEach { option ->
                val isSelected = state.selectedOptionId == option.id
                val isCorrect = option.isCorrect
                
                val backgroundColor = when {
                    isSelected && isCorrect -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                    isSelected && !isCorrect -> Color(0xFFF44336).copy(alpha = 0.2f)
                    else -> accent.copy(alpha = 0.05f)
                }
                
                val borderColor = when {
                    isSelected && isCorrect -> Color(0xFF4CAF50)
                    isSelected && !isCorrect -> Color(0xFFF44336)
                    else -> GhostWhite.copy(alpha = 0.2f)
                }

                Button(
                    onClick = { viewModel.answerQuestion(option.id) },
                    enabled = !state.isTransitioning,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor,
                        disabledContainerColor = backgroundColor,
                        contentColor = GhostWhite
                    ),
                    border = BorderStroke(1.5.dp, borderColor)
                ) {
                    Text(
                        text = option.text.uppercase(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
        
        Spacer(Modifier.height(40.dp))
    }
}
