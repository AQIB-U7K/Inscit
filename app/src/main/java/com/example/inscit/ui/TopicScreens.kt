package com.example.inscit.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscit.*
import com.example.inscit.models.Lang
import com.example.inscit.models.TopicDetail
import com.example.inscit.models.UserNote
import com.example.inscit.syllabus.TopicSyllabus
import kotlin.math.*

@Composable
fun TopicSelectionScreen(
    branch: Branch,
    lang: Lang,
    tts: TTSManager,
    accent: Color,
    txtCol: Color,
    onBack: () -> Unit,
    onTopicClick: (TopicDetail) -> Unit
) {
    val topics = remember(branch, lang) { TopicSyllabus.getTopics(branch, lang) }
    val branchName = if (lang == Lang.EN) branch.name else when(branch) {
        Branch.PHYSICS -> "भौतिकी"
        Branch.CHEMISTRY -> "रसायन विज्ञान"
        Branch.BIOLOGY -> "जीव विज्ञान"
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text("$branchName TOPICS", fontSize = 20.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 2.sp)
        }

        Spacer(Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                if (lang == Lang.EN) "Select a specialized module to explore."
                else "अन्वेषण करने के लिए एक विशेष मॉड्यूल चुनें।",
                color = txtCol.copy(alpha = 0.6f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                val text = if (lang == Lang.EN) "Select a specialized module to explore detailed scientific concepts and interactive simulations."
                else "विस्तृत वैज्ञानिक अवधारणाओं और इंटरैक्टिव सिमुलेशन का पता लगाने के लिए एक विशेष मॉड्यूल चुनें।"
                tts.speak(text)
            }) {
                SpeakerIcon(accent, Modifier.size(20.dp))
            }
        }

        Spacer(Modifier.height(32.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(topics) { topic ->
                Surface(
                    onClick = { onTopicClick(topic) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    color = CardBg,
                    border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.size(40.dp).clip(CircleShape).background(accent.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                (topics.indexOf(topic) + 1).toString(),
                                color = accent,
                                fontWeight = FontWeight.Black
                            )
                        }
                        Spacer(Modifier.width(16.dp))
                        Text(
                            topic.title,
                            color = GhostWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.weight(1f))
                        Text("→", color = accent.copy(alpha = 0.5f))
                    }
                }
            }
        }
    }
}

@Composable
fun TopicDetailScreen(
    topic: TopicDetail,
    lang: Lang,
    tts: TTSManager,
    accent: Color,
    txtCol: Color,
    userNote: UserNote,
    onNoteChange: (UserNote) -> Unit,
    onBack: () -> Unit,
    onLabClick: () -> Unit
) {
    var showNotes by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp).then(if (!showNotes) Modifier.verticalScroll(rememberScrollState()) else Modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                if (showNotes) showNotes = false else onBack()
            }) { BackIcon(color = txtCol) }
            Text(
                if (showNotes) (if (lang == Lang.EN) "OBSERVATIONS" else "ऑब्जर्वेशन") else topic.title.uppercase(),
                fontSize = 18.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 1.sp
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { showNotes = !showNotes }) {
                DrawingIcon(color = if (showNotes) accent else txtCol.copy(alpha = 0.5f))
            }
        }

        Spacer(Modifier.height(32.dp))

        if (showNotes) {
            UserObservationSection(
                branch = topic.title,
                userNote = userNote,
                onNoteChange = onNoteChange,
                accent = accent,
                txtCol = txtCol,
                fullSpace = true,
                lang = lang
            )
        } else {
            // Topic-Specific Interactive Diagram
            TopicInteractiveDiagram(topic, accent)

            Spacer(Modifier.height(32.dp))

            topic.paragraphs.forEachIndexed { index, para ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(CardBg, RoundedCornerShape(16.dp))
                        .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            if (lang == Lang.EN) "CONCEPT ${index + 1}" else "अवधारणा ${index + 1}",
                            color = accent,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        )
                        IconButton(onClick = { tts.speak(para) }, modifier = Modifier.size(24.dp)) {
                            SpeakerIcon(accent, Modifier.size(18.dp))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        para,
                        color = GhostWhite.copy(alpha = 0.8f),
                        fontSize = 15.sp,
                        lineHeight = 24.sp
                    )
                }
            }
            
            Spacer(Modifier.height(24.dp))
            
            Button(
                onClick = onLabClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent.copy(alpha = 0.1f), contentColor = accent),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.3f))
            ) {
                Text(if (lang == Lang.EN) "ENTER RESEARCH LAB" else "अनुसंधान लैब में प्रवेश करें", fontWeight = FontWeight.Bold)
            }
            
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
fun TopicInteractiveDiagram(topic: TopicDetail, accent: Color) {
    var interactionState by remember { mutableStateOf(0f) }
    
    val infiniteTransition = rememberInfiniteTransition(label = "diagram")
    val animationValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart),
        label = "anim"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(CardBg, RoundedCornerShape(24.dp))
            .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(24.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val center = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height / 2f)
            val piF = PI.toFloat()
            
            when (topic.branch) {
                Branch.PHYSICS -> {
                    when (topic.id) {
                        "p1" -> { // Kinematics
                            val x = animationValue * size.width
                            drawLine(accent.copy(alpha = 0.2f), androidx.compose.ui.geometry.Offset(0f, center.y), androidx.compose.ui.geometry.Offset(size.width, center.y), strokeWidth = 2f)
                            drawCircle(accent, radius = 20f, center = androidx.compose.ui.geometry.Offset(x, center.y))
                            // Motion trails
                            for(i in 1..5) {
                                drawCircle(accent.copy(alpha = 0.1f * i.toFloat()), radius = 15f, center = androidx.compose.ui.geometry.Offset(x - (i.toFloat() * 40f), center.y))
                            }
                        }
                        "p2" -> { // Newton
                            val forceX = center.x + sin(animationValue * 2f * piF) * 100f
                            drawRect(accent, topLeft = androidx.compose.ui.geometry.Offset(forceX - 40f, center.y - 40f), size = androidx.compose.ui.geometry.Size(80f, 80f))
                            drawLine(PowerRed, androidx.compose.ui.geometry.Offset(forceX - 120f, center.y), androidx.compose.ui.geometry.Offset(forceX - 40f, center.y), strokeWidth = 8f)
                            // Reaction force
                            drawLine(NeonCyan, androidx.compose.ui.geometry.Offset(forceX + 40f, center.y), androidx.compose.ui.geometry.Offset(forceX + 120f, center.y), strokeWidth = 8f)
                        }
                        "p3" -> { // Energy
                            val y = center.y + sin(animationValue * 2f * piF) * 80f
                            drawCircle(accent, radius = 30f, center = androidx.compose.ui.geometry.Offset(center.x, y))
                            drawRect(accent.copy(alpha = 0.1f), topLeft = androidx.compose.ui.geometry.Offset(center.x - 50f, center.y - 100f), size = androidx.compose.ui.geometry.Size(100f, 200f))
                        }
                        "p4" -> { // Reflection
                            val angle = (animationValue * 0.5f + 0.25f) * piF
                            val rayLen = 200f
                            val startX = center.x - rayLen * cos(angle)
                            val startY = center.y - rayLen * sin(angle)
                            drawLine(accent, androidx.compose.ui.geometry.Offset(startX, startY), center, strokeWidth = 4f)
                            drawLine(accent, center, androidx.compose.ui.geometry.Offset(center.x + rayLen * cos(angle), startY), strokeWidth = 4f)
                            drawLine(GhostWhite, androidx.compose.ui.geometry.Offset(center.x - 100f, center.y), androidx.compose.ui.geometry.Offset(center.x + 100f, center.y), strokeWidth = 4f)
                        }
                        "p5" -> { // Heat
                            for(i in 0..10) {
                                val particleX = center.x + cos(i.toFloat() * 1f + animationValue * 10f) * 60f
                                val particleY = center.y + sin(i.toFloat() * 1.5f + animationValue * 5f) * 60f
                                drawCircle(PowerRed.copy(alpha = 0.6f), radius = 10f, center = androidx.compose.ui.geometry.Offset(particleX, particleY))
                            }
                        }
                    }
                }
                Branch.CHEMISTRY -> {
                    when (topic.id) {
                        "c1" -> { // States
                            val density = (sin(animationValue * 2f * piF) + 1f) / 2f
                            val count = (10f + density * 40f).toInt()
                            for(i in 0..count) {
                                val px = (i.toFloat() * 37f) % size.width
                                val py = (i.toFloat() * 13f + animationValue * 100f) % size.height
                                drawCircle(accent, radius = 8f, center = androidx.compose.ui.geometry.Offset(px, py))
                            }
                        }
                        "c3" -> { // Atomic
                            drawCircle(PowerRed, radius = 25f, center = center)
                            for(i in 0..2) {
                                val radius = 60f + i.toFloat() * 40f
                                drawCircle(accent.copy(alpha = 0.2f), radius = radius, center = center, style = androidx.compose.ui.graphics.drawscope.Stroke(2f))
                                val angle = animationValue * 2f * piF * (i.toFloat() + 1f)
                                val ex = center.x + radius * cos(angle)
                                val ey = center.y + radius * sin(angle)
                                drawCircle(NeonCyan, radius = 8f, center = androidx.compose.ui.geometry.Offset(ex, ey))
                            }
                        }
                        else -> {
                            drawCircle(accent.copy(alpha = 0.2f), radius = 80f * (1f + animationValue * 0.2f), center = center)
                            drawCircle(accent, radius = 50f, center = center)
                        }
                    }
                }
                Branch.BIOLOGY -> {
                    when (topic.id) {
                        "b1" -> { // Cell
                            drawCircle(accent, radius = 100f, center = center, style = androidx.compose.ui.graphics.drawscope.Stroke(4f))
                            drawCircle(TechViolet, radius = 30f, center = center)
                            for(i in 0..5) {
                                val dist = 60f
                                val angle = i.toFloat() * 60f * (piF/180f) + animationValue * 2f * piF
                                drawCircle(BioLime, radius = 10f, center = androidx.compose.ui.geometry.Offset(center.x + dist * cos(angle), center.y + dist * sin(angle)))
                            }
                        }
                        "b4" -> { // Respiration
                            val scale = 0.8f + 0.4f * (sin(animationValue * 2f * piF) + 1f) / 2f
                            drawCircle(accent.copy(alpha = 0.3f), radius = 80f * scale, center = center)
                            drawCircle(accent, radius = 40f * scale, center = center)
                        }
                        else -> {
                            val path = Path()
                            for (i in 0..10) {
                                val px = center.x + cos(animationValue * 2f * piF + i.toFloat()) * 80f
                                val py = center.y - 100f + i.toFloat() * 20f
                                if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
                            }
                            drawPath(path, accent, style = androidx.compose.ui.graphics.drawscope.Stroke(4f))
                        }
                    }
                }
            }
        }
    }
}
