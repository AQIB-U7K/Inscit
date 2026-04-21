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

        Spacer(Modifier.height(48.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
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

        Spacer(Modifier.height(40.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 24.dp)
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
            InteractiveTopicDiagram(topic, accent)

            Spacer(Modifier.height(48.dp))

            topic.paragraphs.forEachIndexed { index, para ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .background(CardBg, RoundedCornerShape(20.dp))
                        .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                        .padding(24.dp)
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            if (lang == Lang.EN) "CONCEPT ${index + 1}" else "अवधारणा ${index + 1}",
                            color = accent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        )
                        IconButton(onClick = { tts.speak(para) }, modifier = Modifier.size(28.dp)) {
                            SpeakerIcon(accent, Modifier.size(20.dp))
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        para,
                        color = GhostWhite.copy(alpha = 0.8f),
                        fontSize = 15.sp,
                        lineHeight = 24.sp
                    )
                }
            }
            
            Spacer(Modifier.height(40.dp))
            
            Button(
                onClick = onLabClick,
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent.copy(alpha = 0.1f), contentColor = accent),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.3f))
            ) {
                Text(if (lang == Lang.EN) "ENTER RESEARCH LAB" else "अनुसंधान लैब में प्रवेश करें", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(Modifier.height(48.dp))
        }
    }
}


