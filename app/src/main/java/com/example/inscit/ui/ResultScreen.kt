package com.example.inscit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscit.*
import com.example.inscit.models.Lang
import com.example.inscit.quiz.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ScienceResultScreen(
    analytics: ScienceAnalytics,
    lang: Lang,
    accent: Color,
    onRetry: () -> Unit,
    onFinish: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = if (lang == Lang.EN) "LAB REPORT" else "लैब रिपोर्ट",
                style = MaterialTheme.typography.labelMedium,
                color = accent,
                letterSpacing = 2.sp
            )
            Text(
                text = "${analytics.overallScore}%",
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                color = GhostWhite
            )
            Text(
                text = if (lang == Lang.EN) analytics.scienceTypeEn else analytics.scienceTypeHi,
                style = MaterialTheme.typography.headlineMedium,
                color = accent,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(32.dp))
        }

        item {
            ScienceRadarChart(
                data = analytics.radarData,
                accent = accent,
                modifier = Modifier
                    .size(280.dp)
                    .padding(16.dp)
            )
            Spacer(Modifier.height(32.dp))
        }

        item {
            ResultTraitsSection(
                title = if (lang == Lang.EN) "STRENGTHS" else "ताकत",
                traits = if (lang == Lang.EN) analytics.strengthsEn else analytics.strengthsHi,
                accent = accent,
                isPositive = true
            )
            Spacer(Modifier.height(16.dp))
            ResultTraitsSection(
                title = if (lang == Lang.EN) "WEAKNESSES" else "कमजोरियां",
                traits = if (lang == Lang.EN) analytics.weaknessesEn else analytics.weaknessesHi,
                accent = PowerRed,
                isPositive = false
            )
            Spacer(Modifier.height(32.dp))
        }

        item {
            Text(
                text = if (lang == Lang.EN) "SCIENTIFIC EXPLANATIONS" else "वैज्ञानिक व्याख्या",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                color = accent,
                fontWeight = FontWeight.Black
            )
            Spacer(Modifier.height(16.dp))
        }

        items(analytics.explanations) { (question, explanation) ->
            ResultExplanationCard(question, explanation, accent)
        }

        item {
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Color.Black)
            ) {
                Text(if (lang == Lang.EN) "RETRY QUIZ" else "पुनः प्रयास करें", fontWeight = FontWeight.Black)
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onFinish,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, accent),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = accent)
            ) {
                Text(if (lang == Lang.EN) "BACK TO HUB" else "हब पर वापस जाएं", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(40.dp))
        }
    }
}

@Composable
fun ScienceRadarChart(data: List<DomainScore>, accent: Color, modifier: Modifier = Modifier) {
    val outlineColor = GhostWhite.copy(alpha = 0.1f)

    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2.2f
        val sides = data.size
        val angleStep = 2 * Math.PI / sides

        // Draw background web
        for (i in 1..4) {
            val r = radius * (i / 4f)
            val path = Path()
            for (j in 0 until sides) {
                val angle = j * angleStep - Math.PI / 2
                val x = center.x + r * cos(angle).toFloat()
                val y = center.y + r * sin(angle).toFloat()
                if (j == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            path.close()
            drawPath(path, outlineColor, style = Stroke(width = 1.dp.toPx()))
        }

        // Draw Axis
        for (j in 0 until sides) {
            val angle = j * angleStep - Math.PI / 2
            drawLine(
                color = outlineColor,
                start = center,
                end = Offset(
                    center.x + radius * cos(angle).toFloat(),
                    center.y + radius * sin(angle).toFloat()
                ),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Draw actual data
        val dataPath = Path()
        data.forEachIndexed { index, score ->
            val angle = index * angleStep - Math.PI / 2
            val r = radius * score.score.coerceIn(0.15f, 1f)
            val x = center.x + r * cos(angle).toFloat()
            val y = center.y + r * sin(angle).toFloat()
            if (index == 0) dataPath.moveTo(x, y) else dataPath.lineTo(x, y)
        }
        dataPath.close()

        drawPath(dataPath, accent.copy(alpha = 0.2f))
        drawPath(dataPath, accent, style = Stroke(width = 2.dp.toPx()))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ResultTraitsSection(title: String, traits: List<String>, accent: Color, isPositive: Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, color = accent, fontWeight = FontWeight.Black, fontSize = 12.sp, letterSpacing = 2.sp)
        Spacer(Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (traits.isEmpty()) {
                Text(
                    text = if (isPositive) "Analyzing..." else "No major weaknesses",
                    color = GhostWhite.copy(alpha = 0.5f),
                    fontSize = 14.sp
                )
            } else {
                traits.forEach { trait ->
                    Surface(
                        color = accent.copy(alpha = 0.1f),
                        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            trait,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = accent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultExplanationCard(question: String, explanation: String, accent: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = question,
                style = MaterialTheme.typography.titleSmall,
                color = accent,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = GhostWhite.copy(alpha = 0.8f),
                lineHeight = 20.sp
            )
        }
    }
}
