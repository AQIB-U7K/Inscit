package com.example.inscit

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscit.models.Lang
import com.example.inscit.models.QuizProgress
import com.example.inscit.models.ThemeMode
import com.example.inscit.models.TopicDetail
import com.example.inscit.models.UserDocument
import com.example.inscit.models.UserNote
import com.example.inscit.models.UserProfile
import com.example.inscit.models.UserSettings
import com.example.inscit.models.UserStats
import com.example.inscit.syllabus.Syllabus
import com.example.inscit.ui.AtomIcon
import com.example.inscit.ui.BackIcon
import com.example.inscit.ui.DNAIcon
import com.example.inscit.ui.DrawingIcon
import com.example.inscit.ui.ExportIcon
import com.example.inscit.ui.FlaskIcon
import com.example.inscit.ui.NoteIcon
import com.example.inscit.ui.ProfileImage
import com.example.inscit.ui.SaveIcon
import com.example.inscit.ui.ScienceQuizScreen
import com.example.inscit.ui.ShareIcon
import com.example.inscit.ui.SpeakerIcon
import com.example.inscit.ui.StarIcon
import com.example.inscit.ui.TopicDetailScreen
import com.example.inscit.ui.TopicSelectionScreen
import com.example.inscit.xp.Rank
import androidx.compose.runtime.MutableState
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

enum class Screen { SPLASH, HOME, LAB, QUIZ, NOTES, THEME_CONFIG, NOTES_FOLDER, PROFILE, TOPIC_SELECTION, TOPIC_DETAIL, EXPORTS_LIST, EXPORT_DETAIL }
enum class Branch { PHYSICS, CHEMISTRY, BIOLOGY }

val DeepSpace = Color(0xFF020408)
val NeonCyan = Color(0xFF00F2FF)
val TechViolet = Color(0xFF7000FF)
val BioLime = Color(0xFF39FF14)
val PowerRed = Color(0xFFFF003C)
val GhostWhite = Color(0xFFF8F9FA)
val DimSlate = Color(0xFF4A5568)
val NobleLightBg = Color(0xFFFDFDFD)
val NobleDarkBg = Color(0xFF121212)
val NobleLightAccent = Color(0xFF2C3E50)
val NobleDarkAccent = Color(0xFFE0E0E0)
val CardBg = Color(0xFF1A1C1E)

fun triggerVibration(context: Context, type: String) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = when (type) {
            "CLICK" -> VibrationEffect.createOneShot(10, VibrationEffect.DEFAULT_AMPLITUDE)
            "SUCCESS" -> VibrationEffect.createWaveform(longArrayOf(0, 20, 10, 30), intArrayOf(0, 128, 0, 255), -1)
            else -> VibrationEffect.createOneShot(5, 50)
        }
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        val duration = when (type) {
            "CLICK" -> 10L
            "SUCCESS" -> 50L
            else -> 5L
        }
        vibrator.vibrate(duration)
    }
}

private const val PREFS_NAME = "inscit_prefs"
private const val KEY_USER_DATA = "user_data"

fun saveUserDocument(context: Context, userDoc: UserDocument) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val notesStr = userDoc.userNotes.entries.joinToString("|||") { (k, v) -> 
        val content = v.content.replace(":", "\\:").replace(";", "\\;").replace("~", "\\~").replace("|", "\\|")
        val drawing = v.drawingData.replace(":", "\\:").replace(";", "\\;").replace("~", "\\~").replace("|", "\\|")
        "$k~~~$content~~~$drawing"
    }
    val data = "${userDoc.profile.name}|${userDoc.profile.photoUrl ?: ""}|${userDoc.stats.xp}|${userDoc.stats.level}|${userDoc.stats.quizzesTaken}|${userDoc.quizProgress.lastScore}|${userDoc.settings.language.name}|${userDoc.settings.theme.name}|$notesStr"
    prefs.edit().putString(KEY_USER_DATA, data).apply()
}

fun saveProfileImageLocally(context: Context, uri: Uri): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(context.filesDir, "profile_pic.jpg")
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()
        Uri.fromFile(file).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun loadUserDocument(context: Context): UserDocument {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val data = prefs.getString(KEY_USER_DATA, null) ?: return UserDocument(profile = UserProfile(name = "Core Explorer"))
    val parts = data.split("|")
    return try {
        val notesStr = if (parts.size > 8) parts[8] else ""
        val notes = if (notesStr.isEmpty()) emptyMap() else {
            notesStr.split("|||").associate { 
                val noteParts = it.split("~~~")
                val k = noteParts[0]
                val content = if (noteParts.size > 1) noteParts[1].replace("\\:", ":").replace("\\;", ";").replace("\\~", "~").replace("\\|", "|") else ""
                val drawing = if (noteParts.size > 2) noteParts[2].replace("\\:", ":").replace("\\;", ";").replace("\\~", "~").replace("\\|", "|") else ""
                k to UserNote(content, drawing)
            }
        }
        UserDocument(
            profile = UserProfile(name = parts[0], photoUrl = parts[1].takeIf { it.isNotEmpty() }),
            stats = UserStats(xp = parts[2].toInt(), level = parts[3].toInt(), quizzesTaken = parts[4].toInt()),
            quizProgress = QuizProgress(lastScore = parts[5].toFloat()),
            settings = UserSettings(
                language = if (parts.size > 6) Lang.valueOf(parts[6]) else Lang.EN,
                theme = if (parts.size > 7) ThemeMode.valueOf(parts[7]) else ThemeMode.NEON
            ),
            userNotes = notes
        )
    } catch (e: Exception) {
        UserDocument(profile = UserProfile(name = "Core Explorer"))
    }
}

class TTSManager(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
            isReady = true
        }
    }

    fun speak(text: String) {
        if (isReady) tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}

fun getExportFolder(context: Context): File {
    val folder = File(context.getExternalFilesDir(null), "InscitExports")
    if (!folder.exists()) folder.mkdirs()
    return folder
}

fun saveToTextFile(context: Context, userDoc: UserDocument) {
    val data = """
        INSCIT EXPLORER PROFILE
        =======================
        Name: ${userDoc.profile.name}
        Level: ${userDoc.stats.level}
        Total XP: ${userDoc.stats.xp}
        Quizzes Taken: ${userDoc.stats.quizzesTaken}
        Last Score: ${userDoc.quizProgress.lastScore}%
        Joined: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(java.util.Date(userDoc.profile.createdAt))}
        
        STRENGTHS:
        ${userDoc.quizProgress.strengths.joinToString("\n")}
        
        WEAKNESSES:
        ${userDoc.quizProgress.weaknesses.joinToString("\n")}
    """.trimIndent()
    
    try {
        val folder = getExportFolder(context)
        val fileName = "inscit_profile_${System.currentTimeMillis()}.txt"
        val file = File(folder, fileName)
        file.writeText(data)
        Toast.makeText(context, "Progress Saved to: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "save processing failed: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

private fun buttonContentColor(background: Color): Color {
    return if (background.luminance() > 0.5f) Color.Black else GhostWhite
}

class MainActivity : ComponentActivity() {
    private lateinit var ttsManager: TTSManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ttsManager = TTSManager(this)
        setContent { AppEngine(ttsManager) }
    }

    override fun onDestroy() {
        ttsManager.shutdown()
        super.onDestroy()
    }
}


// Custom Saver for UserDocument to ensure perfect persistence
val UserDocumentSaver = Saver<UserDocument, String>(
    save = { doc ->
        val notesStr = doc.userNotes.entries.joinToString("|||") { (k, v) -> 
            val content = v.content.replace(":", "\\:").replace(";", "\\;").replace("~", "\\~").replace("|", "\\|")
            val drawing = v.drawingData.replace(":", "\\:").replace(";", "\\;").replace("~", "\\~").replace("|", "\\|")
            "$k~~~$content~~~$drawing"
        }
        "${doc.profile.name}|${doc.profile.photoUrl ?: ""}|${doc.stats.xp}|${doc.stats.level}|${doc.stats.quizzesTaken}|${doc.quizProgress.lastScore}|${doc.settings.language.name}|${doc.settings.theme.name}|$notesStr"
    },
    restore = { data ->
        val parts = data.split("|")
        try {
            val notesStr = if (parts.size > 8) parts[8] else ""
            val notes = if (notesStr.isEmpty()) emptyMap() else {
                notesStr.split("|||").associate { 
                    val noteParts = it.split("~~~")
                    val k = noteParts[0]
                    val content = if (noteParts.size > 1) noteParts[1].replace("\\:", ":").replace("\\;", ";").replace("\\~", "~").replace("\\|", "|") else ""
                    val drawing = if (noteParts.size > 2) noteParts[2].replace("\\:", ":").replace("\\;", ";").replace("\\~", "~").replace("\\|", "|") else ""
                    k to UserNote(content, drawing)
                }
            }
            UserDocument(
                profile = UserProfile(name = parts[0], photoUrl = parts[1].takeIf { it.isNotEmpty() }),
                stats = UserStats(xp = parts[2].toInt(), level = parts[3].toInt(), quizzesTaken = parts[4].toInt()),
                quizProgress = QuizProgress(lastScore = parts[5].toFloat()),
                settings = UserSettings(
                    language = if (parts.size > 6) Lang.valueOf(parts[6]) else Lang.EN,
                    theme = if (parts.size > 7) ThemeMode.valueOf(parts[7]) else ThemeMode.NEON
                ),
                userNotes = notes
            )
        } catch (e: Exception) {
            UserDocument(profile = UserProfile(name = "Core Explorer"))
        }
    }
)

@Composable
fun AppEngine(tts: TTSManager) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    // Local user state with robust persistence and rememberSaveable
    var userDocument by rememberSaveable(
        saver = Saver(
            save = { with(UserDocumentSaver) { save(it.value) } },
            restore = { mutableStateOf(with(UserDocumentSaver) { restore(it)!! }) }
        )
    ) { 
        mutableStateOf(loadUserDocument(context)) 
    }

    var currentScreen by rememberSaveable { mutableStateOf(Screen.SPLASH) }
    var selectedBranch by rememberSaveable { mutableStateOf(Branch.PHYSICS) }
    var selectedTopic by remember { mutableStateOf<TopicDetail?>(null) }
    var selectedExportFile by remember { mutableStateOf<File?>(null) }
    
    // Derived state from userDocument
    val language = userDocument.settings.language
    val themeMode = userDocument.settings.theme

    // Immediate persistence to SharedPreferences
    LaunchedEffect(userDocument) {
        saveUserDocument(context, userDocument)
    }

    BackHandler(enabled = currentScreen != Screen.HOME && currentScreen != Screen.SPLASH) {
        when (currentScreen) {
            Screen.TOPIC_SELECTION -> currentScreen = Screen.HOME
            Screen.TOPIC_DETAIL -> currentScreen = Screen.TOPIC_SELECTION
            Screen.THEME_CONFIG -> currentScreen = Screen.HOME
            Screen.NOTES_FOLDER -> currentScreen = Screen.THEME_CONFIG
            Screen.LAB -> {
                tts.stop()
                currentScreen = Screen.HOME
            }
            Screen.NOTES -> {
                tts.stop()
                currentScreen = Screen.LAB
            }
            Screen.QUIZ -> {
                tts.stop()
                currentScreen = Screen.HOME
            }
            Screen.PROFILE -> currentScreen = Screen.HOME
            Screen.EXPORTS_LIST -> currentScreen = Screen.PROFILE
            Screen.EXPORT_DETAIL -> currentScreen = Screen.EXPORTS_LIST
            else -> {}
        }
    }

    val appBg = when {
        themeMode == ThemeMode.NOBLE && !isDark -> NobleLightBg
        themeMode == ThemeMode.NOBLE && isDark -> NobleDarkBg
        else -> DeepSpace
    }
    val primaryAccent = if (themeMode == ThemeMode.NOBLE) {
        if (isDark) NobleDarkAccent else NobleLightAccent
    } else {
        NeonCyan
    }
    val textColor = if (themeMode == ThemeMode.NOBLE && !isDark) Color(0xFF1A1A1A) else GhostWhite

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = appBg) {
            Crossfade(targetState = currentScreen, animationSpec = tween(600), label = "screen_crossfade") { target ->
                when (target) {
                    Screen.SPLASH -> FullSplashScreen(primaryAccent) {
                        triggerVibration(context, "CLICK")
                        currentScreen = Screen.HOME
                    }
                    Screen.HOME -> {
                        ModernHome(
                            lang = language,
                            theme = themeMode,
                            txtCol = textColor,
                            accent = primaryAccent,
                            totalXp = userDocument.stats.xp,
                            userName = userDocument.profile.name,
                            photoUrl = userDocument.profile.photoUrl?.let { Uri.parse(it) },
                            onLangChange = { newLang ->
                                userDocument = userDocument.copy(settings = userDocument.settings.copy(language = newLang))
                            },
                            onNav = { branch ->
                                triggerVibration(context, "CLICK")
                                selectedBranch = branch
                                currentScreen = Screen.TOPIC_SELECTION
                            },
                            onQuiz = {
                                triggerVibration(context, "SUCCESS")
                                currentScreen = Screen.QUIZ
                            },
                            onTheme = {
                                triggerVibration(context, "CLICK")
                                currentScreen = Screen.THEME_CONFIG
                            },
                            onProfile = {
                                triggerVibration(context, "CLICK")
                                currentScreen = Screen.PROFILE
                            }
                        )
                    }
                    Screen.TOPIC_SELECTION -> TopicSelectionScreen(
                        branch = selectedBranch,
                        lang = language,
                        tts = tts,
                        accent = primaryAccent,
                        txtCol = textColor,
                        onBack = { currentScreen = Screen.HOME },
                        onTopicClick = { topic ->
                            selectedTopic = topic
                            currentScreen = Screen.TOPIC_DETAIL
                        }
                    )
                    Screen.TOPIC_DETAIL -> selectedTopic?.let { topic ->
                        TopicDetailScreen(
                            topic = topic,
                            lang = language,
                            tts = tts,
                            accent = primaryAccent,
                            txtCol = textColor,
                            userNote = userDocument.userNotes[topic.id] ?: UserNote(),
                            onNoteChange = { updatedNote ->
                                val updatedNotes = userDocument.userNotes.toMutableMap()
                                updatedNotes[topic.id] = updatedNote
                                userDocument = userDocument.copy(userNotes = updatedNotes)
                            },
                            onBack = { currentScreen = Screen.TOPIC_SELECTION },
                            onLabClick = { currentScreen = Screen.LAB }
                        )
                    }
                    Screen.THEME_CONFIG -> ThemeSelectionScreen(
                        current = themeMode,
                        lang = language,
                        accent = primaryAccent,
                        txtCol = textColor,
                        onToggle = { newTheme ->
                            userDocument = userDocument.copy(settings = userDocument.settings.copy(theme = newTheme))
                        },
                        onLangToggle = { newLang ->
                            userDocument = userDocument.copy(settings = userDocument.settings.copy(language = newLang))
                        },
                        onOpenFolder = { currentScreen = Screen.NOTES_FOLDER },
                        onViewNote = { branch ->
                            selectedBranch = branch
                            currentScreen = Screen.NOTES
                        },
                        onBack = { currentScreen = Screen.HOME }
                    )
                    Screen.NOTES_FOLDER -> NotesFolderScreen(
                        lang = language,
                        accent = primaryAccent,
                        txtCol = textColor,
                        onBack = { currentScreen = Screen.THEME_CONFIG },
                        onOpenNote = { branch, lang ->
                            selectedBranch = branch
                            userDocument = userDocument.copy(settings = userDocument.settings.copy(language = lang))
                            currentScreen = Screen.NOTES
                        }
                    )
                    Screen.LAB -> LabScreen(
                        branch = selectedBranch,
                        lang = language,
                        tts = tts,
                        accent = primaryAccent,
                        txtCol = textColor,
                        onBack = {
                            tts.stop()
                            currentScreen = Screen.HOME
                        },
                        onNotes = { currentScreen = Screen.NOTES }
                    )
                    Screen.NOTES -> NotesScreen(
                        branch = selectedBranch,
                        lang = language,
                        tts = tts,
                        accent = primaryAccent,
                        txtCol = textColor,
                        userNote = (userDocument.userNotes[selectedBranch.name] ?: UserNote()) as UserNote,
                        onNoteChange = { newNote ->
                            val updatedNotes = userDocument.userNotes.toMutableMap()
                            updatedNotes[selectedBranch.name] = newNote
                            userDocument = userDocument.copy(userNotes = updatedNotes)
                        },
                        onBack = { 
                            tts.stop()
                            currentScreen = Screen.LAB 
                        },
                        onSave = {
                            saveUserDocument(context, userDocument)
                            Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Screen.QUIZ -> {
                        ScienceQuizScreen(
                            lang = language,
                            accent = primaryAccent,
                            onFinish = { xpEarned, score, strengths, weaknesses ->
                                tts.stop()
                                val newXp = userDocument.stats.xp + xpEarned
                                val newStats = userDocument.stats.copy(
                                    xp = newXp,
                                    level = (newXp / 100) + 1,
                                    quizzesTaken = userDocument.stats.quizzesTaken + 1
                                )
                                val newProgress = userDocument.quizProgress.copy(
                                    lastScore = score.toFloat(),
                                    strengths = strengths,
                                    weaknesses = weaknesses
                                )
                                userDocument = userDocument.copy(stats = newStats, quizProgress = newProgress)
                                currentScreen = Screen.HOME
                            }
                        )
                    }
                    Screen.PROFILE -> {
                        LocalProfileView(
                            userDoc = userDocument,
                            accent = primaryAccent,
                            onUpdateProfile = { updated ->
                                userDocument = updated
                            },
                            onSaveProgress = {
                                saveToTextFile(context, userDocument)
                            },
                            onViewExports = {
                                currentScreen = Screen.EXPORTS_LIST
                            },
                            onBack = { currentScreen = Screen.HOME }
                        )
                    }
                    Screen.EXPORTS_LIST -> {
                        ExportListScreen(
                            accent = primaryAccent,
                            txtCol = textColor,
                            lang = language,
                            onBack = { currentScreen = Screen.PROFILE },
                            onFileClick = { file ->
                                selectedExportFile = file
                                currentScreen = Screen.EXPORT_DETAIL
                            }
                        )
                    }
                    Screen.EXPORT_DETAIL -> {
                        selectedExportFile?.let { file ->
                            ExportDetailScreen(
                                file = file,
                                accent = primaryAccent,
                                txtCol = textColor,
                                lang = language,
                                onBack = { currentScreen = Screen.EXPORTS_LIST }
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun NotesFolderScreen(
    lang: Lang,
    accent: Color,
    txtCol: Color,
    onBack: () -> Unit,
    onOpenNote: (Branch, Lang) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text(if (lang == Lang.EN) "KNOWLEDGE HUB" else "नॉलेज हब", fontSize = 20.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 2.sp)
        }
        
        Spacer(Modifier.height(40.dp))
        
        Branch.entries.forEach { branch ->
            Surface(
                onClick = { onOpenNote(branch, lang) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                color = CardBg,
                border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
            ) {
                Row(Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(40.dp).clip(CircleShape).background(accent.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
                        when(branch) {
                            Branch.PHYSICS -> AtomIcon(accent)
                            Branch.CHEMISTRY -> FlaskIcon(accent)
                            Branch.BIOLOGY -> DNAIcon(accent)
                        }
                    }
                    Spacer(Modifier.width(20.dp))
                    Column {
                        Text(if (lang == Lang.EN) branch.name else when(branch) {
                            Branch.PHYSICS -> "भौतिकी"
                            Branch.CHEMISTRY -> "रसायन विज्ञान"
                            Branch.BIOLOGY -> "जीव विज्ञान"
                        }, fontWeight = FontWeight.Bold, color = GhostWhite, fontSize = 18.sp)
                        Text(if (lang == Lang.EN) "SYLLABUS MODULES" else "सिलेबस मॉड्यूल", fontSize = 10.sp, color = accent, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.weight(1f))
                    Text("→", color = GhostWhite.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Composable
fun LabScreen(
    branch: Branch,
    lang: Lang,
    tts: TTSManager,
    accent: Color,
    txtCol: Color,
    onBack: () -> Unit,
    onNotes: () -> Unit
) {
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
            Text("$branchName LAB", fontSize = 20.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 2.sp)
        }
        
        Spacer(Modifier.height(60.dp))
        
        InteractiveDiagram(branch, accent)
        
        Spacer(Modifier.height(40.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                if (lang == Lang.EN) "EXPERIMENTAL DATA" else "प्रायोगिक डेटा",
                fontSize = 14.sp, fontWeight = FontWeight.Bold, color = accent
            )
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = { 
                val text = if (lang == Lang.EN) "Access the detailed scientific syllabus and interactive modules." 
                          else "विस्तृत वैज्ञानिक पाठ्यक्रम और इंटरैक्टिव मॉड्यूल तक पहुंचें।"
                tts.speak(text) 
            }, modifier = Modifier.size(20.dp)) {
                SpeakerIcon(accent, Modifier.size(16.dp))
            }
        }
        
        Spacer(Modifier.height(20.dp))
        
        Text(
            if (lang == Lang.EN) "Access the detailed scientific syllabus and interactive modules." 
            else "विस्तृत वैज्ञानिक पाठ्यक्रम और इंटरैक्टिव मॉड्यूल तक पहुंचें।",
            textAlign = TextAlign.Center,
            color = txtCol.copy(alpha = 0.7f),
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        
        Spacer(Modifier.weight(1f))
        
        Button(
            onClick = onNotes,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = DeepSpace)
        ) {
            Text(if (lang == Lang.EN) "OPEN MODULE NOTES" else "मॉड्यूल नोट्स खोलें", fontWeight = FontWeight.ExtraBold)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun InteractiveDiagram(branch: Branch, accent: Color, modifier: Modifier = Modifier) {
    var interactionState by remember { mutableStateOf(0f) }
    var frequency by remember { mutableFloatStateOf(2f) }
    // Initial value based on branch
    var scaleFactor by remember { mutableFloatStateOf(if (branch == Branch.CHEMISTRY) 3f else 1f) }
    
    val animatedInteraction by animateFloatAsState(
        targetValue = interactionState,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
        label = "diagram_animation"
    )

    LaunchedEffect(Unit) {
        while(true) {
            withFrameMillis { 
                interactionState += 0.05f * frequency
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(CardBg, RoundedCornerShape(24.dp))
                .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(24.dp))
                .padding(16.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                when (branch) {
                    Branch.PHYSICS -> {
                        val waveHeight = 50f * scaleFactor
                        val path = Path()
                        for (i in 0..size.width.toInt()) {
                            val x = i.toFloat()
                            val y = size.height / 2 + waveHeight * kotlin.math.sin((x / size.width) * 2 * Math.PI.toFloat() * frequency + animatedInteraction)
                            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                        }
                        drawPath(path, accent, style = Stroke(width = 4f, cap = StrokeCap.Round))
                    }
                    Branch.CHEMISTRY -> {
                        val center = Offset(size.width / 2, size.height / 2)
                        val numShells = scaleFactor.toInt().coerceIn(1, 7)
                        val shellCapacities = listOf(2, 8, 18, 32, 50, 72, 98)
                        
                        // Draw Nucleus
                        drawCircle(accent, radius = 18f, center = center)
                        drawCircle(GhostWhite.copy(alpha = 0.3f), radius = 10f, center = center)
                        
                        val maxRadius = (size.minDimension / 2.2f)
                        val shellSpacing = (maxRadius - 40f) / 7f
                        
                        for (orbit in 0 until numShells) {
                            val radius = 50f + orbit * shellSpacing
                            drawCircle(accent.copy(alpha = 0.15f), radius = radius, center = center, style = Stroke(width = 1.5f))
                            
                            val electronCount = shellCapacities[orbit]
                            for (i in 0 until electronCount) {
                                val angleOffset = (2 * Math.PI.toFloat() / electronCount) * i
                                // "Do not increase speed of rotation" - use consistent base speed
                                val electronAngle = animatedInteraction + angleOffset
                                val ex = center.x + radius * kotlin.math.cos(electronAngle)
                                val ey = center.y + radius * kotlin.math.sin(electronAngle)
                                drawCircle(GhostWhite, radius = 4f, center = Offset(ex, ey))
                                // Electron glow
                                drawCircle(accent.copy(alpha = 0.3f), radius = 6f, center = Offset(ex, ey))
                            }
                        }
                    }
                    Branch.BIOLOGY -> {
                        val center = Offset(size.width / 2, size.height / 2)
                        val baseRadius = 120f
                        
                        // Cell Membrane
                        drawCircle(accent.copy(alpha = 0.05f), radius = baseRadius, center = center)
                        drawCircle(accent, radius = baseRadius, center = center, style = Stroke(width = 4f))
                        
                        // Cytoplasm streaming effect with ribosomes and mitochondria
                        val activity = scaleFactor
                        val organelleCount = (12 * activity).toInt()
                        for (i in 0 until organelleCount) {
                            val dist = (baseRadius * 0.35f) + (i * 7f) % (baseRadius * 0.55f)
                            val drift = animatedInteraction * 0.2f
                            val angle = drift + (i * 137.5f * (Math.PI.toFloat() / 180f))
                            val ox = center.x + dist * kotlin.math.cos(angle)
                            val oy = center.y + dist * kotlin.math.sin(angle)
                            
                            if (i % 4 == 0) {
                                // Mitochondria (Oval)
                                val mColor = Color(0xFFFFA500)
                                drawCircle(mColor.copy(alpha = 0.6f), radius = 8f, center = Offset(ox, oy))
                                drawCircle(mColor, radius = 8f, center = Offset(ox, oy), style = Stroke(width = 1f))
                            } else {
                                // Ribosome
                                drawCircle(GhostWhite.copy(alpha = 0.8f), radius = 2.5f, center = Offset(ox, oy))
                            }
                        }

                        // Nucleus
                        val nucleusRadius = 35f
                        drawCircle(TechViolet.copy(alpha = 0.2f), radius = nucleusRadius, center = center)
                        drawCircle(TechViolet, radius = nucleusRadius, center = center, style = Stroke(width = 2f))
                        
                        // DNA representation
                        for (i in 0 until 6) {
                            val py = center.y - nucleusRadius + (i + 1) * (nucleusRadius * 2 / 7f)
                            val wave = kotlin.math.sin(animatedInteraction + i) * 12f
                            drawCircle(GhostWhite.copy(alpha = 0.6f), radius = 3f, center = Offset(center.x + wave, py))
                            drawCircle(GhostWhite.copy(alpha = 0.6f), radius = 3f, center = Offset(center.x - wave, py))
                            drawLine(GhostWhite.copy(alpha = 0.3f), Offset(center.x - wave, py), Offset(center.x + wave, py), strokeWidth = 1f)
                        }
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(DeepSpace.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text("LEGEND", fontSize = 8.sp, fontWeight = FontWeight.Black, color = accent)
                when(branch) {
                    Branch.PHYSICS -> {
                        LegendItem("Wave Propagation", accent)
                        LegendItem("Frequency: ${"%.1f".format(frequency)}Hz", GhostWhite.copy(alpha = 0.5f))
                    }
                    Branch.CHEMISTRY -> {
                        LegendItem("Nucleus (Proton/Neutron)", accent)
                        LegendItem("Shells (K-Q)", accent.copy(alpha = 0.4f))
                        LegendItem("Electrons (2n²)", GhostWhite)
                    }
                    Branch.BIOLOGY -> {
                        LegendItem("Cell Membrane", accent)
                        LegendItem("Mitochondria", Color(0xFFFFA500))
                        LegendItem("Nucleus & DNA", TechViolet)
                    }
                }
            }
        }
        
        Spacer(Modifier.height(16.dp))
        
        // Interactive Controls
        Column(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("RATE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GhostWhite.copy(alpha = 0.5f), modifier = Modifier.width(40.dp))
                Slider(
                    value = frequency,
                    onValueChange = { frequency = it },
                    valueRange = 0.5f..5f,
                    modifier = Modifier.weight(1f),
                    colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                val magLabel = if (branch == Branch.CHEMISTRY) "SHELLS" else "MAG"
                Text(magLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GhostWhite.copy(alpha = 0.5f), modifier = Modifier.width(40.dp))
                Slider(
                    value = scaleFactor,
                    onValueChange = { scaleFactor = it },
                    valueRange = if (branch == Branch.CHEMISTRY) 1f..7f else 0.5f..2.0f,
                    steps = if (branch == Branch.CHEMISTRY) 5 else 0,
                    modifier = Modifier.weight(1f),
                    colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent)
                )
            }
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(6.dp).background(color, CircleShape))
        Spacer(Modifier.width(6.dp))
        Text(label, fontSize = 8.sp, color = GhostWhite.copy(alpha = 0.7f))
    }
}

@Composable
fun NotesScreen(
    branch: Branch,
    lang: Lang,
    tts: TTSManager,
    accent: Color,
    txtCol: Color,
    userNote: UserNote,
    onNoteChange: (UserNote) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    val notes = remember(branch, lang) { Syllabus.getSyllabusNotes(branch, lang) }
    var showObservations by remember { mutableStateOf(false) }
    val branchName = if (lang == Lang.EN) branch.name else when(branch) {
        Branch.PHYSICS -> "भौतिकी"
        Branch.CHEMISTRY -> "रसायन विज्ञान"
        Branch.BIOLOGY -> "जीव विज्ञान"
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text(
                if (showObservations) (if (lang == Lang.EN) "$branchName DECK" else "$branchName डेक") else (if (lang == Lang.EN) "$branchName NOTES" else "$branchName नोट्स"), 
                fontSize = 18.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 1.sp
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { showObservations = !showObservations }) { 
                DrawingIcon(color = if (showObservations) accent else txtCol.copy(alpha = 0.5f)) 
            }
            IconButton(onClick = onSave) { SaveIcon(color = accent) }
        }
        
        Spacer(Modifier.height(32.dp))
        
        if (showObservations) {
            // Dedicated full-space observation deck
            UserObservationSection(
                branch = branchName,
                userNote = userNote,
                onNoteChange = onNoteChange,
                accent = accent,
                txtCol = txtCol,
                fullSpace = true,
                lang = lang
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                item {
                    InteractiveDiagram(branch, accent)
                }
                
                items(notes) { note ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CardBg, RoundedCornerShape(16.dp))
                            .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(note.title, color = accent, fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
                            IconButton(onClick = { tts.speak(note.content) }, modifier = Modifier.size(24.dp)) {
                                SpeakerIcon(accent, Modifier.size(18.dp))
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(note.content, color = GhostWhite.copy(alpha = 0.8f), fontSize = 15.sp, lineHeight = 24.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun LocalProfileView(
    userDoc: UserDocument,
    accent: Color,
    onUpdateProfile: (UserDocument) -> Unit,
    onSaveProgress: () -> Unit,
    onViewExports: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val lang = userDoc.settings.language
    var editedName by remember { mutableStateOf(userDoc.profile.name) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val localPath = saveProfileImageLocally(context, it)
            val updated = userDoc.copy(profile = userDoc.profile.copy(photoUrl = localPath ?: it.toString()))
            onUpdateProfile(updated)
            triggerVibration(context, "SUCCESS")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = GhostWhite) }
            Text(if (lang == Lang.EN) "USER CORE" else "यूज़र कोर", fontSize = 20.sp, fontWeight = FontWeight.Black, color = GhostWhite, letterSpacing = 2.sp)
        }

        Spacer(Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(accent.copy(alpha = 0.1f))
                .clickable { imagePickerLauncher.launch("image/*") }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            ProfileImage(
                photoUrl = userDoc.profile.photoUrl?.let { Uri.parse(it) },
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                placeholderColor = accent
            )
            Box(
                modifier = Modifier.align(Alignment.BottomEnd).size(36.dp).clip(CircleShape).background(accent).padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("+", color = DeepSpace, fontWeight = FontWeight.Black, fontSize = 20.sp)
            }
        }

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = editedName,
            onValueChange = { 
                editedName = it
                onUpdateProfile(userDoc.copy(profile = userDoc.profile.copy(name = it)))
            },
            label = { Text(if (lang == Lang.EN) "CORE IDENTIFIER" else "कोर पहचानकर्ता", color = accent, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            textStyle = androidx.compose.ui.text.TextStyle(color = GhostWhite, fontWeight = FontWeight.Black, fontSize = 18.sp, letterSpacing = 2.sp),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accent,
                unfocusedBorderColor = GhostWhite.copy(alpha = 0.2f),
                cursorColor = accent
            )
        )

        Spacer(Modifier.height(12.dp))

        Text(
            (if (lang == Lang.EN) "RANK: " else "रैंक: ") + Rank.fromXp(userDoc.stats.xp).label.uppercase(),
            fontSize = 12.sp,
            color = accent,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(48.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatItem(label = if (lang == Lang.EN) "LEVEL" else "लेवल", value = userDoc.stats.level.toString(), accent)
            StatItem(label = if (lang == Lang.EN) "TOTAL XP" else "कुल XP", value = userDoc.stats.xp.toString(), accent)
            StatItem(label = if (lang == Lang.EN) "QUIZES TAKEN" else " दिए गए क्विज़", value = userDoc.stats.quizzesTaken.toString(), accent)
        }

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = onSaveProgress,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = DeepSpace)
        ) {
            Text(if (lang == Lang.EN) "EXPORT CORE DATA" else "कोर डेटा एक्सपोर्ट करें", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onViewExports,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GhostWhite.copy(alpha = 0.05f), contentColor = GhostWhite),
            border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.2f))
        ) {
            Text(if (lang == Lang.EN) "VIEW EXPORTS" else "एक्सपोर्ट्स देखें", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
        }

        Spacer(Modifier.height(24.dp))
    }
}
@Composable
fun StatItem(label: String, value: String, accent: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 24.sp, fontWeight = FontWeight.Black, color = GhostWhite)
        Text(label, fontSize = 10.sp, color = accent.copy(alpha = 0.7f), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FullSplashScreen(accent: Color, onExplore: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        HexagonGrid(accent.copy(alpha = 0.08f))
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(
                "INSCIT",
                fontSize = 80.sp,
                fontWeight = FontWeight.Black,
                color = GhostWhite,
                letterSpacing = 10.sp
            )
            Spacer(Modifier.height(4.dp))
            Text("OMEGA CORE V9.0", color = accent, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 4.sp)
            Spacer(Modifier.height(60.dp))
            Button(
                onClick = onExplore,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = buttonContentColor(accent))
            ) {
                Text("INITIALIZE", fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            }
        }
    }
}

@Composable
fun HexagonGrid(color: Color) {
    Canvas(Modifier.fillMaxSize()) {
        val step = 100f
        for (x in 0..(size.width / step).toInt()) {
            for (y in 0..(size.height / step).toInt()) {
                val offset = if (y % 2 == 0) 0f else step / 2
                drawCircle(color, radius = 2f, center = Offset(x * step + offset, y * step))
            }
        }
    }
}

@Composable
fun ModernHome(
    lang: Lang,
    theme: ThemeMode,
    txtCol: Color,
    accent: Color,
    totalXp: Int,
    userName: String,
    photoUrl: Uri?,
    onLangChange: (Lang) -> Unit,
    onNav: (Branch) -> Unit,
    onQuiz: () -> Unit,
    onTheme: () -> Unit,
    onProfile: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    if (lang == Lang.EN) "SYSTEM ONLINE" else "सिस्टम ऑनलाइन",
                    color = accent,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    if (lang == Lang.EN) "Hello, $userName" else "नमस्ते, $userName",
                    color = GhostWhite,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
            }
            
            Surface(
                onClick = onProfile,
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = CardBg,
                border = BorderStroke(1.dp, accent.copy(alpha = 0.3f))
            ) {
                ProfileImage(
                    photoUrl = photoUrl,
                    modifier = Modifier.fillMaxSize(),
                    placeholderColor = accent
                )
            }
        }
        
        Spacer(Modifier.height(40.dp))
        
        // Stats Overview
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = CardBg,
            border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(if (lang == Lang.EN) "CURRENT XP" else "कुल XP", fontSize = 10.sp, color = accent, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Text(totalXp.toString(), fontSize = 32.sp, fontWeight = FontWeight.Black, color = GhostWhite)
                }
                Box(
                    modifier = Modifier.size(60.dp).clip(CircleShape).background(accent.copy(alpha = 0.1f)).border(1.dp, accent.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    StarIcon(accent, Modifier.size(30.dp))
                }
            }
        }

        // Featured Research Module
        Text(
            if (lang == Lang.EN) "PLATFORM OVERVIEW" else "प्लेटफ़ॉर्म अवलोकन",
            color = accent,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(16.dp))
        
        Surface(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            shape = RoundedCornerShape(24.dp),
            color = CardBg,
            border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
        ) {
            Box(Modifier.padding(24.dp), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        if (lang == Lang.EN) "Welcome to the Inscit Omega Core." else "Inscit ओमेगा कोर में आपका स्वागत है।",
                        color = GhostWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        if (lang == Lang.EN) "Select a branch below to begin your scientific journey with interactive simulations and detailed modules." 
                        else "इंटरैक्टिव सिमुलेशन और विस्तृत मॉड्यूल के साथ अपनी वैज्ञानिक यात्रा शुरू करने के लिए नीचे एक शाखा चुनें।",
                        color = GhostWhite.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(32.dp))
        
        // Quick Actions
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ActionCard(if (lang == Lang.EN) "INITIATE QUIZ" else "क्विज़ शुरू करें", accent, Modifier.weight(1.3f), onQuiz)
            ActionCard(if (lang == Lang.EN) "SETTINGS" else "सेटिंग्स", GhostWhite.copy(alpha = 0.05f), Modifier.weight(0.7f), onTheme)
        }
        
        Spacer(Modifier.height(40.dp))
        
        Text(
            if (lang == Lang.EN) "SCIENCE BRANCHES" else "विज्ञान की शाखाएं",
            color = accent,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(Modifier.height(16.dp))
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(Branch.entries) { branch ->
                BranchCard(branch, lang, accent, onNav)
            }
        }
    }
}

@Composable
fun ActionCard(label: String, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(20.dp),
        color = if (color == GhostWhite.copy(alpha = 0.05f)) CardBg else color,
        border = if (color == GhostWhite.copy(alpha = 0.05f)) BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f)) else null
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                label,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = if (color.luminance() > 0.5f && color != GhostWhite.copy(alpha = 0.05f)) DeepSpace else GhostWhite,
                fontSize = if (label.length > 12) 11.sp else 13.sp
            )
        }
    }
}

@Composable
fun BranchCard(branch: Branch, lang: Lang, accent: Color, onNav: (Branch) -> Unit) {
    Surface(
        onClick = { onNav(branch) },
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
                modifier = Modifier.size(44.dp).clip(CircleShape).background(accent.copy(alpha = 0.08f)),
                contentAlignment = Alignment.Center
            ) {
                when(branch) {
                    Branch.PHYSICS -> AtomIcon(accent, Modifier.size(24.dp))
                    Branch.CHEMISTRY -> FlaskIcon(accent, Modifier.size(24.dp))
                    Branch.BIOLOGY -> DNAIcon(accent, Modifier.size(24.dp))
                }
            }
            Spacer(Modifier.width(20.dp))
            Column {
                Text(if (lang == Lang.EN) branch.name else when(branch) {
                    Branch.PHYSICS -> "भौतिकी"
                    Branch.CHEMISTRY -> "रसायन विज्ञान"
                    Branch.BIOLOGY -> "जीव विज्ञान"
                }, fontWeight = FontWeight.Bold, color = GhostWhite, fontSize = 16.sp)
                Text(if (lang == Lang.EN) "MODULE READY" else "मॉड्यूल तैयार", fontSize = 9.sp, color = accent.copy(alpha = 0.6f), fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.weight(1f))
            Text("→", color = GhostWhite.copy(alpha = 0.2f), fontSize = 20.sp)
        }
    }
}

@Composable
fun ThemeSelectionScreen(
    current: ThemeMode,
    lang: Lang,
    accent: Color,
    txtCol: Color,
    onToggle: (ThemeMode) -> Unit,
    onLangToggle: (Lang) -> Unit,
    onOpenFolder: () -> Unit,
    onViewNote: (Branch) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text(if (lang == Lang.EN) "SETTINGS" else "सेटिंग्स", fontSize = 20.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 2.sp)
        }
        
        Spacer(Modifier.height(60.dp))

        Text(if (lang == Lang.EN) "APP LANGUAGE" else "ऐप की भाषा", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 2.sp, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(16.dp))
        LanguageSlider(lang, accent, onLangToggle)

        Spacer(Modifier.height(32.dp))
        
        Text(if (lang == Lang.EN) "INTERFACE THEME" else "इंटरफ़ेस थीम", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 2.sp, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(16.dp))
        
        ThemeItem(if (lang == Lang.EN) "NEON PROTOCOL" else "नियॉन प्रोटोकॉल", ThemeMode.NEON, current == ThemeMode.NEON, accent) { onToggle(ThemeMode.NEON) }
        Spacer(Modifier.height(12.dp))
        ThemeItem(if (lang == Lang.EN) "NOBLE ARCHIVE" else "नोबल आर्काइव", ThemeMode.NOBLE, current == ThemeMode.NOBLE, accent) { onToggle(ThemeMode.NOBLE) }
        
        Spacer(Modifier.height(48.dp))
        
        Text(if (lang == Lang.EN) "KNOWLEDGE BASE" else "नॉलेज बेस", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 2.sp, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(16.dp))
        
        Surface(
            onClick = onOpenFolder,
            modifier = Modifier.fillMaxWidth().height(64.dp),
            shape = RoundedCornerShape(16.dp),
            color = CardBg,
            border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
        ) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                NoteIcon(color = accent)
                Spacer(Modifier.width(16.dp))
                Text(if (lang == Lang.EN) "EXPLORE ALL NOTES" else "सभी नोट्स देखें", color = GhostWhite, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("→", color = GhostWhite.copy(alpha = 0.2f))
            }
        }
        
        Spacer(Modifier.height(32.dp))
        
        Text(if (lang == Lang.EN) "SAVED OBSERVATIONS" else "सेव की गई ऑब्जर्वेशन", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 2.sp, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(16.dp))
        
        Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Branch.entries.forEach { branch ->
                Surface(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = CardBg.copy(alpha = 0.5f)
                ) {
                    Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).background(accent, CircleShape))
                        Spacer(Modifier.width(12.dp))
                        Text(if (lang == Lang.EN) branch.name else when(branch) {
                            Branch.PHYSICS -> "भौतिकी"
                            Branch.CHEMISTRY -> "रसायन विज्ञान"
                            Branch.BIOLOGY -> "जीव विज्ञान"
                        }, color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.weight(1f))
                        Text(if (lang == Lang.EN) "VIEW" else "देखें", color = accent, fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.clickable { 
                            onViewNote(branch)
                        })
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))
        
        Text(
            "INSCIT OMEGA v9.0.4",
            color = txtCol.copy(alpha = 0.3f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun LanguageSlider(current: Lang, accent: Color, onToggle: (Lang) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        shape = RoundedCornerShape(16.dp),
        color = CardBg,
        border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (current == Lang.EN) accent else Color.Transparent)
                    .clickable { onToggle(Lang.EN) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "ENGLISH", 
                    color = if (current == Lang.EN) DeepSpace else GhostWhite, 
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (current == Lang.HI) accent else Color.Transparent)
                    .clickable { onToggle(Lang.HI) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "HINDI", 
                    color = if (current == Lang.HI) DeepSpace else GhostWhite, 
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun ThemeItem(label: String, mode: ThemeMode, isSelected: Boolean, accent: Color, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(64.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) accent.copy(alpha = 0.1f) else CardBg,
        border = if (isSelected) BorderStroke(2.dp, accent) else null
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(label, color = if (isSelected) accent else GhostWhite, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            if (isSelected) Text("ACTIVE", color = accent, fontSize = 10.sp, fontWeight = FontWeight.Black)
        }
    }
}
@Composable
fun UserObservationSection(
    branch: String,
    userNote: UserNote,
    onNoteChange: (UserNote) -> Unit,
    accent: Color,
    txtCol: Color,
    fullSpace: Boolean = false,
    lang: Lang = Lang.EN
) {
    val context = LocalContext.current
    var text by remember(userNote.content) { mutableStateOf(userNote.content) }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (fullSpace) Modifier.fillMaxHeight() else Modifier.wrapContentHeight())
            .background(CardBg, RoundedCornerShape(16.dp))
            .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(if (lang == Lang.EN) "YOUR OBSERVATIONS" else "आपकी ऑब्जर्वेशन", color = accent, fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
            Spacer(Modifier.weight(1f))
            if (fullSpace) {
                IconButton(onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Inscit Observations ($branch):\n\n${userNote.content}")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Observations"))
                }, modifier = Modifier.size(24.dp)) { ShareIcon(accent) }
                Spacer(Modifier.width(8.dp))
                IconButton(onClick = {
                    try {
                        val fileName = "inscit_note_${branch}_${System.currentTimeMillis()}.txt"
                        val file = File(context.getExternalFilesDir(null), fileName)
                        file.writeText("BRANCH: $branch\n\nOBSERVATIONS:\n${userNote.content}")
                        Toast.makeText(context, "Exported to ${file.name}", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Export failed", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.size(24.dp)) { ExportIcon(accent) }
            }
        }
        
        Spacer(Modifier.height(12.dp))
        
        TextField(
            value = text,
            onValueChange = { 
                text = it
                onNoteChange(userNote.copy(content = it))
            },
            placeholder = { Text(if (lang == Lang.EN) "Record your findings..." else "अपने निष्कर्ष दर्ज करें...", color = txtCol.copy(alpha = 0.3f)) },
            modifier = Modifier.fillMaxWidth().then(if (fullSpace) Modifier.height(120.dp) else Modifier),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = accent,
                unfocusedIndicatorColor = accent.copy(alpha = 0.2f),
                cursorColor = accent,
                focusedTextColor = GhostWhite,
                unfocusedTextColor = GhostWhite
            )
        )
        
        Spacer(Modifier.height(if (fullSpace) 24.dp else 16.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            DrawingIcon(accent, Modifier.size(16.dp))
            Spacer(Modifier.width(8.dp))
            Text(if (lang == Lang.EN) "SKETCHPAD" else "स्केचपैड", color = accent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            if (userNote.drawingData.isNotEmpty()) {
                TextButton(onClick = { onNoteChange(userNote.copy(drawingData = "")) }) {
                    Text(if (lang == Lang.EN) "CLEAR" else "साफ करें", color = PowerRed, fontSize = 10.sp)
                }
            }
        }
        
        Spacer(Modifier.height(8.dp))
        
        DrawingCanvas(
            drawingData = userNote.drawingData,
            onDrawingChange = { onNoteChange(userNote.copy(drawingData = it)) },
            color = accent,
            modifier = if (fullSpace) Modifier.weight(1f) else Modifier.height(200.dp)
        )
        
        if (!fullSpace) {
            Spacer(Modifier.height(16.dp))
            
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Inscit Observations ($branch):\n\n${userNote.content}")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Observations"))
                }) { ShareIcon(accent) }

                IconButton(onClick = {
                    try {
                        val fileName = "inscit_note_${branch}_${System.currentTimeMillis()}.txt"
                        val file = File(context.getExternalFilesDir(null), fileName)
                        file.writeText("BRANCH: $branch\n\nOBSERVATIONS:\n${userNote.content}")
                        Toast.makeText(context, "Exported to ${file.name}", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Export failed", Toast.LENGTH_SHORT).show()
                    }
                }) { ExportIcon(accent) }
            }
        }
    }
}

@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    drawingData: String,
    onDrawingChange: (String) -> Unit,
    color: Color
) {
    var currentPath by remember { mutableStateOf<List<Offset>>(emptyList()) }
    val paths = remember(drawingData) {
        if (drawingData.isEmpty()) mutableListOf<List<Offset>>()
        else drawingData.split("|").map { pathStr ->
            pathStr.split(";").mapNotNull { pointStr ->
                val coords = pointStr.split(",")
                if (coords.size == 2) Offset(coords[0].toFloat(), coords[1].toFloat()) else null
            }
        }.toMutableList()
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .background(DeepSpace.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> currentPath = listOf(offset) },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        currentPath = currentPath + change.position
                    },
                    onDragEnd = {
                        val newPaths = paths + listOf(currentPath)
                        val newData = newPaths.joinToString("|") { path ->
                            path.joinToString(";") { "${it.x},${it.y}" }
                        }
                        onDrawingChange(newData)
                        currentPath = emptyList()
                    }
                )
            }
    ) {        paths.forEach { path ->
            if (path.size > 1) {
                val p = Path().apply {
                    moveTo(path[0].x, path[0].y)
                    path.drop(1).forEach { lineTo(it.x, it.y) }
                }
                drawPath(p, color, style = Stroke(width = 4f, cap = StrokeCap.Round))
            }
        }
        if (currentPath.size > 1) {
            val p = Path().apply {
                moveTo(currentPath[0].x, currentPath[0].y)
                currentPath.drop(1).forEach { lineTo(it.x, it.y) }
            }
            drawPath(p, color, style = Stroke(width = 4f, cap = StrokeCap.Round))
        }
    }
}

@Composable
fun ExportListScreen(
    accent: Color,
    txtCol: Color,
    lang: Lang,
    onBack: () -> Unit,
    onFileClick: (File) -> Unit
) {
    val context = LocalContext.current
    val exportFolder = remember { getExportFolder(context) }
    var files by remember { mutableStateOf(exportFolder.listFiles()?.filter { it.isFile }?.sortedByDescending { it.lastModified() } ?: emptyList()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text(if (lang == Lang.EN) "EXPORTED DATA" else "एक्सपोर्ट किया गया डेटा", fontSize = 20.sp, fontWeight = FontWeight.Black, color = txtCol, letterSpacing = 2.sp)
        }

        Spacer(Modifier.height(32.dp))

        if (files.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(if (lang == Lang.EN) "No exports found." else "कोई एक्सपोर्ट नहीं मिला।", color = txtCol.copy(alpha = 0.5f))
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(files) { file ->
                    Surface(
                        onClick = { onFileClick(file) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = CardBg,
                        border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
                    ) {
                        Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(40.dp).clip(CircleShape).background(accent.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
                                NoteIcon(accent)
                            }
                            Spacer(Modifier.width(16.dp))
                            Column(Modifier.weight(1f)) {
                                Text(file.name, color = GhostWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(
                                    java.text.SimpleDateFormat("dd MMM yyyy, HH:mm").format(java.util.Date(file.lastModified())),
                                    color = accent.copy(alpha = 0.6f),
                                    fontSize = 10.sp
                                )
                            }
                            IconButton(onClick = {
                                file.delete()
                                files = exportFolder.listFiles()?.filter { it.isFile }?.sortedByDescending { it.lastModified() } ?: emptyList()
                            }) {
                                Text("×", color = PowerRed, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExportDetailScreen(
    file: File,
    accent: Color,
    txtCol: Color,
    lang: Lang,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val content = remember(file) { try { file.readText() } catch (e: Exception) { "Error reading file" } }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { BackIcon(color = txtCol) }
            Text(file.name, fontSize = 16.sp, fontWeight = FontWeight.Black, color = txtCol, modifier = Modifier.weight(1f))
            IconButton(onClick = {
                val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, uri)
                    type = "text/plain"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                context.startActivity(Intent.createChooser(shareIntent, "Share Export"))
            }) { ShareIcon(accent) }
        }

        Spacer(Modifier.height(24.dp))

        Surface(
            modifier = Modifier.fillMaxWidth().weight(1f),
            shape = RoundedCornerShape(24.dp),
            color = CardBg,
            border = BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
        ) {
            Column(Modifier.padding(24.dp).verticalScroll(rememberScrollState())) {
                Text(content, color = GhostWhite.copy(alpha = 0.8f), fontSize = 14.sp, lineHeight = 22.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            }
        }
        
        Spacer(Modifier.height(24.dp))
        
        Button(
            onClick = {
                val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                context.startActivity(Intent.createChooser(intent, "Export Data"))
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = DeepSpace)
        ) {
            Text(if (lang == Lang.EN) "SHARE / EXPORT" else "शेयर / एक्सपोर्ट", fontWeight = FontWeight.ExtraBold)
        }
    }
}
