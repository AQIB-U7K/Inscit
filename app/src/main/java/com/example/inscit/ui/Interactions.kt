package com.example.inscit.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inscit.*
import com.example.inscit.models.TopicDetail
import kotlin.math.*

@Composable
fun InteractiveTopicDiagram(topic: TopicDetail, accent: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBg, RoundedCornerShape(24.dp))
            .border(1.dp, GhostWhite.copy(alpha = 0.05f), RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        when (topic.branch) {
            Branch.PHYSICS -> PhysicsInteractions(topic, accent)
            Branch.CHEMISTRY -> ChemistryInteractions(topic, accent)
            Branch.BIOLOGY -> BiologyInteractions(topic, accent)
        }
    }
}

@Composable
fun PhysicsInteractions(topic: TopicDetail, accent: Color) {
    when (topic.id) {
        "p1" -> KinematicsInteraction(accent)
        "p2" -> NewtonsLawInteraction(accent)
        "p3" -> EnergyInteraction(accent)
        "p4" -> ReflectionInteraction(accent)
        "p5" -> HeatTransferInteraction(accent)
        else -> DefaultPlaceholder(topic, accent)
    }
}

@Composable
fun ChemistryInteractions(topic: TopicDetail, accent: Color) {
    when (topic.id) {
        "c1" -> StatesOfMatterInteraction(accent)
        "c2" -> ElementsMixturesInteraction(accent)
        "c3" -> AtomModelInteraction(accent)
        "c4" -> ChemicalChangeInteraction(accent)
        "c5" -> AcidsBasesInteraction(accent)
        else -> DefaultPlaceholder(topic, accent)
    }
}

@Composable
fun BiologyInteractions(topic: TopicDetail, accent: Color) {
    when (topic.id) {
        "b1" -> CellInteraction(accent)
        "b2" -> PlantTissueInteraction(accent)
        "b3" -> NutritionInteraction(accent)
        "b4" -> RespirationInteraction(accent)
        "b5" -> PlantReproductionInteraction(accent)
        else -> DefaultPlaceholder(topic, accent)
    }
}

@Composable
fun KinematicsInteraction(accent: Color) {
    var velocity by remember { mutableFloatStateOf(2f) }
    var dragX by remember { mutableFloatStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition(label = "kinematics")
    val animTime by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(tween(5000, easing = LinearEasing), RepeatMode.Restart),
        label = "time"
    )

    Column {
        Text("Kinematics: Velocity & Displacement", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text("Drag the object to manually move it", color = accent.copy(alpha = 0.5f), fontSize = 10.sp)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(120.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize().pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    dragX += dragAmount.x
                }
            }) {
                val x = ((animTime * velocity * 5f) + dragX) % size.width
                val y = size.height / 2f
                
                drawLine(Color.Gray.copy(alpha = 0.3f), Offset(0f, y + 20f), Offset(size.width, y + 20f), strokeWidth = 2f)
                drawCircle(accent, radius = 15f, center = Offset(x, y))
                drawCircle(accent.copy(alpha = 0.3f), radius = 20f, center = Offset(x, y), style = Stroke(2f))
            }
        }
        
        Spacer(Modifier.height(16.dp))
        Text("Velocity: ${"%.1f".format(velocity)} m/s", color = accent, fontSize = 12.sp)
        Slider(value = velocity, onValueChange = { velocity = it }, valueRange = 0.5f..10f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun NewtonsLawInteraction(accent: Color) {
    var force by remember { mutableFloatStateOf(50f) }
    var mass by remember { mutableFloatStateOf(10f) }
    val acceleration = force / mass
    val infiniteTransition = rememberInfiniteTransition(label = "newton")
    val animValue by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
        label = "anim"
    )

    Column {
        Text("Newton's Second Law: F = ma", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(140.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val boxSize = 40f + mass * 2f
                val centerX = size.width / 2f
                val centerY = size.height / 2f
                
                drawRect(accent, Offset(centerX - boxSize/2, centerY - boxSize/2), Size(boxSize, boxSize))
                val arrowLen = force * 1.5f
                drawLine(PowerRed, Offset(centerX - boxSize/2 - arrowLen, centerY), Offset(centerX - boxSize/2, centerY), strokeWidth = 6f)
                val path = Path().apply {
                    moveTo(centerX - boxSize/2, centerY)
                    lineTo(centerX - boxSize/2 - 15f, centerY - 10f)
                    lineTo(centerX - boxSize/2 - 15f, centerY + 10f)
                    close()
                }
                drawPath(path, PowerRed)
                val accX = (animValue * acceleration * 20f) % 100f
                drawCircle(NeonCyan, radius = 5f, center = Offset(centerX + boxSize/2 + 20f + accX, centerY))
            }
        }
        
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.weight(1f)) {
                Text("Force: ${force.toInt()} N", color = PowerRed, fontSize = 12.sp)
                Slider(value = force, onValueChange = { force = it }, valueRange = 10f..100f, colors = SliderDefaults.colors(thumbColor = PowerRed, activeTrackColor = PowerRed))
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text("Mass: ${mass.toInt()} kg", color = accent, fontSize = 12.sp)
                Slider(value = mass, onValueChange = { mass = it }, valueRange = 5f..50f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
            }
        }
        Text("Acceleration: ${"%.2f".format(acceleration)} m/s²", color = NeonCyan, fontSize = 14.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun EnergyInteraction(accent: Color) {
    var height by remember { mutableFloatStateOf(80f) }
    val infiniteTransition = rememberInfiniteTransition(label = "energy")
    val swing by infiniteTransition.animateFloat(
        initialValue = -1f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "swing"
    )

    Column {
        Text("Potential vs Kinetic Energy", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(160.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, 20f)
                val length = 100f
                val angle = swing * (height / 100f) * 0.8f
                val endX = center.x + length * sin(angle)
                val endY = center.y + length * cos(angle)
                
                drawLine(GhostWhite.copy(alpha = 0.3f), center, Offset(endX, endY), strokeWidth = 2f)
                drawCircle(accent, radius = 15f, center = Offset(endX, endY))
                
                val pe = (1f - kotlin.math.abs(swing)) * 40f
                val ke = kotlin.math.abs(swing) * 40f
                drawRect(PowerRed, Offset(10f, size.height - 10f - pe), Size(20f, pe))
                drawRect(NeonCyan, Offset(40f, size.height - 10f - ke), Size(20f, ke))
            }
            Text("PE", Modifier.align(Alignment.BottomStart).padding(start = 10.dp, bottom = 45.dp), color = PowerRed, fontSize = 8.sp)
            Text("KE", Modifier.align(Alignment.BottomStart).padding(start = 40.dp, bottom = 45.dp), color = NeonCyan, fontSize = 8.sp)
        }
        
        Spacer(Modifier.height(16.dp))
        Text("Drop Height: ${height.toInt()}", color = accent, fontSize = 12.sp)
        Slider(value = height, onValueChange = { height = it }, valueRange = 20f..100f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun ReflectionInteraction(accent: Color) {
    var angle by remember { mutableFloatStateOf(45f) }
    
    Column {
        Text("Optics: Law of Reflection", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text("Adjust angle to see reflection change", color = accent.copy(alpha = 0.5f), fontSize = 10.sp)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(180.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height - 20f)
                val rad = Math.toRadians(angle.toDouble()).toFloat()
                val lineLen = 250f
                drawLine(Color.Gray, Offset(20f, size.height - 20f), Offset(size.width - 20f, size.height - 20f), strokeWidth = 4f)
                drawLine(GhostWhite.copy(alpha = 0.2f), center, Offset(center.x, 20f), strokeWidth = 2f)
                val ix = center.x - lineLen * sin(rad)
                val iy = center.y - lineLen * cos(rad)
                drawLine(accent, Offset(ix, iy), center, strokeWidth = 4f)
                val rx = center.x + lineLen * sin(rad)
                val ry = center.y - lineLen * cos(rad)
                drawLine(PowerRed, center, Offset(rx, ry), strokeWidth = 4f)
            }
        }
        
        Spacer(Modifier.height(16.dp))
        Text("Angle of Incidence: ${angle.toInt()}°", color = accent, fontSize = 12.sp)
        Slider(value = angle, onValueChange = { angle = it }, valueRange = 0f..85f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun HeatTransferInteraction(accent: Color) {
    var conductionMode by remember { mutableStateOf(true) }
    val infiniteTransition = rememberInfiniteTransition(label = "heat")
    val flow by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
        label = "flow"
    )

    Column {
        Text("Thermodynamics: Heat Flow", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(140.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val centerY = size.height / 2f
                drawCircle(PowerRed, radius = 30f, center = Offset(40f, centerY))
                if(conductionMode) {
                    drawRect(Color.Gray, Offset(70f, centerY - 10f), Size(size.width - 140f, 20f))
                    for(i in 0..5) {
                        val x = 80f + i * 40f + (flow * 40f)
                        if(x < size.width - 80f) drawCircle(PowerRed.copy(alpha = 1f - (x/size.width)), radius = 4f, center = Offset(x, centerY))
                    }
                } else {
                    val path = Path().apply { addOval(androidx.compose.ui.geometry.Rect(Offset(100f, centerY - 50f), Size(150f, 100f))) }
                    drawPath(path, NeonCyan.copy(alpha = 0.3f), style = Stroke(2f))
                }
            }
        }
        
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Mode: ${if(conductionMode) "Conduction" else "Convection"}", color = accent, fontSize = 12.sp, modifier = Modifier.weight(1f))
            Switch(checked = conductionMode, onCheckedChange = { conductionMode = it })
        }
    }
}

@Composable
fun StatesOfMatterInteraction(accent: Color) {
    var temp by remember { mutableFloatStateOf(20f) }
    val infiniteTransition = rememberInfiniteTransition(label = "matter")
    val vibration by infiniteTransition.animateFloat(
        initialValue = -1f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(100, easing = LinearEasing), RepeatMode.Reverse),
        label = "vibration"
    )

    Column {
        Text("Molecular Kinetic Theory", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        
        Box(Modifier.height(160.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val rows = 4
                val cols = 6
                val spacing = 40f
                val startX = (size.width - (cols-1)*spacing) / 2
                val startY = (size.height - (rows-1)*spacing) / 2
                val vibScale = (temp / 100f) * 10f
                for(r in 0 until rows) {
                    for(c in 0 until cols) {
                        val offset = Offset(startX + c * spacing + (vibration * vibScale * ((r+c)%2)), startY + r * spacing + (vibration * vibScale * ((r*c)%2)))
                        drawCircle(brush = Brush.radialGradient(listOf(accent, accent.copy(alpha = 0.5f)), center = offset, radius = 12f), radius = 12f, center = offset)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        val stateText = when { temp < 30 -> "SOLID"; temp < 70 -> "LIQUID"; else -> "GAS" }
        Text("Temperature: ${temp.toInt()}°C - $stateText", color = if(temp > 70) PowerRed else accent, fontSize = 12.sp)
        Slider(value = temp, onValueChange = { temp = it }, valueRange = 0f..100f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun ElementsMixturesInteraction(accent: Color) {
    var showMixture by remember { mutableStateOf(false) }
    Column {
        Text("Pure Substances vs Mixtures", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(140.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val cols = 6
                val rows = 3
                val spacing = 35f
                val startX = (size.width - (cols-1)*spacing) / 2
                val startY = (size.height - (rows-1)*spacing) / 2
                for(r in 0 until rows) {
                    for(c in 0 until cols) {
                        val pos = Offset(startX + c * spacing, startY + r * spacing)
                        val color = if (showMixture && (r + c) % 2 != 0) TechViolet else accent
                        drawCircle(color, radius = 8f, center = pos)
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(if(showMixture) "Mixture" else "Element", color = accent, fontSize = 12.sp, modifier = Modifier.weight(1f))
            Switch(checked = showMixture, onCheckedChange = { showMixture = it })
        }
    }
}

@Composable
fun AtomModelInteraction(accent: Color) {
    var protons by remember { mutableIntStateOf(1) }
    var dragAngle by remember { mutableFloatStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition(label = "atom")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotation"
    )

    Column {
        Text("Atomic Structure: Bohr Model", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(160.dp).fillMaxWidth().pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                dragAngle += dragAmount.x
            }
        }, contentAlignment = Alignment.Center) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                for(i in 0 until protons) {
                    val angle = (i * 360f / protons).toDouble()
                    val ox = center.x + 8f * cos(Math.toRadians(angle)).toFloat()
                    val oy = center.y + 8f * sin(Math.toRadians(angle)).toFloat()
                    drawCircle(PowerRed, radius = 6f, center = Offset(ox, oy))
                }
                val shells = if(protons <= 2) 1 else 2
                for(s in 1..shells) {
                    val radius = 40f + s * 30f
                    drawCircle(accent.copy(alpha = 0.2f), radius = radius, center = center, style = Stroke(1f))
                    val electrons = if(s == 1) kotlin.math.min(protons, 2) else kotlin.math.max(0, protons - 2)
                    for(e in 0 until electrons) {
                        val angle = rotation + dragAngle + (e * 360f / kotlin.math.max(1, electrons))
                        val ex = center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
                        val ey = center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
                        drawCircle(NeonCyan, radius = 4f, center = Offset(ex, ey))
                    }
                }
            }
        }
        Text("Protons: $protons", color = PowerRed, fontSize = 12.sp)
        Slider(value = protons.toFloat(), onValueChange = { protons = it.toInt() }, valueRange = 1f..10f, steps = 8, colors = SliderDefaults.colors(thumbColor = PowerRed, activeTrackColor = PowerRed))
    }
}

@Composable
fun ChemicalChangeInteraction(accent: Color) {
    var reactionProgress by remember { mutableFloatStateOf(0f) }
    Column {
        Text("Chemical Reaction: Synthesis", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(140.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val centerX = size.width / 2f
                val centerY = size.height / 2f
                if (reactionProgress < 0.8f) {
                    val offset = (1f - reactionProgress) * 100f
                    drawCircle(PowerRed, radius = 15f, center = Offset(centerX - offset - 20f, centerY))
                    drawCircle(NeonCyan, radius = 15f, center = Offset(centerX + offset + 20f, centerY))
                } else {
                    drawCircle(TechViolet, radius = 25f, center = Offset(centerX, centerY))
                    drawCircle(TechViolet.copy(alpha = 0.3f), radius = 35f, center = Offset(centerX, centerY))
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Slider(value = reactionProgress, onValueChange = { reactionProgress = it }, valueRange = 0f..1f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun AcidsBasesInteraction(accent: Color) {
    var ph by remember { mutableFloatStateOf(7f) }
    val phColor = when { ph < 3f -> Color.Red; ph < 6f -> Color(0xFFFFA500); ph < 8f -> Color.Green; ph < 11f -> Color.Blue; else -> Color(0xFF4B0082) }
    Column {
        Text("pH Scale & Indicators", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(100.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(phColor.copy(alpha = 0.2f))) {
            Canvas(Modifier.fillMaxSize()) { drawCircle(phColor, radius = 30f + (kotlin.math.abs(ph-7f) * 2f), center = Offset(size.width/2f, size.height/2f)) }
            Text("pH: ${"%.1f".format(ph)}", Modifier.align(Alignment.Center), color = phColor, fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.height(16.dp))
        Slider(value = ph, onValueChange = { ph = it }, valueRange = 0f..14f, colors = SliderDefaults.colors(thumbColor = phColor, activeTrackColor = phColor))
    }
}

@Composable
fun CellInteraction(accent: Color) {
    var selectedOrganelle by remember { mutableStateOf("None") }
    var scale by remember { mutableFloatStateOf(1f) }
    val infiniteTransition = rememberInfiniteTransition(label = "cell_pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("The Living Cell: Interactive Explorer", color = GhostWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text("Pinch/Drag to zoom • Tap organelles to inspect", color = accent.copy(alpha = 0.6f), fontSize = 11.sp)
        
        Spacer(Modifier.height(16.dp))
        
        Box(
            Modifier
                .heightIn(min = 200.dp, max = 300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black.copy(alpha = 0.4f))
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        scale = (scale + dragAmount.y / 1000f).coerceIn(0.7f, 2.5f)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val baseRadius = min(size.width, size.height) * 0.35f * scale * pulse
                
                // Cytoplasm / Membrane
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to BioLime.copy(alpha = 0.15f),
                        0.8f to BioLime.copy(alpha = 0.05f),
                        1.0f to BioLime.copy(alpha = 0.3f),
                        center = center,
                        radius = baseRadius
                    ),
                    radius = baseRadius,
                    center = center
                )
                drawCircle(BioLime.copy(alpha = 0.4f), radius = baseRadius, center = center, style = Stroke(3f))

                // Nucleus
                val nucRadius = baseRadius * 0.4f
                val nucColor = if(selectedOrganelle == "Nucleus") TechViolet else TechViolet.copy(alpha = 0.7f)
                drawCircle(nucColor, radius = nucRadius, center = center)
                drawCircle(Color.Black.copy(alpha = 0.3f), radius = nucRadius * 0.3f, center = center) // Nucleolus

                // Mitochondria
                val mitoRadius = baseRadius * 0.15f
                val mitoPos = Offset(center.x + baseRadius * 0.5f, center.y - baseRadius * 0.4f)
                val mitoColor = if(selectedOrganelle == "Mitochondria") PowerRed else PowerRed.copy(alpha = 0.7f)
                drawOval(mitoColor, topLeft = Offset(mitoPos.x - mitoRadius, mitoPos.y - mitoRadius/2), size = Size(mitoRadius * 2, mitoRadius))
                // Inner membrane (cristae)
                for(i in 0..3) {
                    drawLine(Color.White.copy(alpha = 0.5f), 
                        Offset(mitoPos.x - mitoRadius + (i*mitoRadius*0.5f), mitoPos.y - mitoRadius/4),
                        Offset(mitoPos.x - mitoRadius + (i*mitoRadius*0.5f) + 5f, mitoPos.y + mitoRadius/4),
                        strokeWidth = 2f)
                }

                // Ribosomes
                repeat(15) { i ->
                    val angle = (i * 24).toDouble()
                    val dist = baseRadius * 0.6f + (i % 3) * 10f
                    drawCircle(accent.copy(alpha = 0.5f), radius = 2f, 
                        center = Offset(center.x + dist * cos(Math.toRadians(angle)).toFloat(), center.y + dist * sin(Math.toRadians(angle)).toFloat()))
                }
            }
            
            // Info Overlay
            if (selectedOrganelle != "None") {
                Surface(
                    Modifier.align(Alignment.TopEnd).padding(12.dp),
                    color = CardBg.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, accent.copy(alpha = 0.3f))
                ) {
                    Text(
                        when(selectedOrganelle) {
                            "Nucleus" -> "NUCLEUS: Genetic Control Center"
                            "Mitochondria" -> "MITOCHONDRIA: Powerhouse (ATP)"
                            else -> ""
                        },
                        Modifier.padding(8.dp),
                        color = accent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OrganelleChip("Nucleus", selectedOrganelle == "Nucleus", Modifier.weight(1f)) { selectedOrganelle = it }
            OrganelleChip("Mitochondria", selectedOrganelle == "Mitochondria", Modifier.weight(1f)) { selectedOrganelle = it }
        }
    }
}

@Composable
fun PlantTissueInteraction(accent: Color) {
    var tissueType by remember { mutableStateOf("Xylem") }
    Column {
        Text("Plant Tissues: Vascular Transport", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(160.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                if(tissueType == "Xylem") {
                    for(i in 0..5) {
                        val x = 40f + i * 60f
                        drawLine(accent.copy(alpha = 0.3f), Offset(x, 0f), Offset(x, size.height), strokeWidth = 15f)
                        val flow = (System.currentTimeMillis() % 2000) / 2000f
                        drawCircle(NeonCyan, radius = 4f, center = Offset(x, size.height * (1f - flow)))
                    }
                } else {
                    for(i in 0..3) {
                        val y = i * 40f + 20f
                        drawLine(BioLime.copy(alpha = 0.5f), Offset(0f, y), Offset(size.width, y), strokeWidth = 2f)
                        val flow = (System.currentTimeMillis() % 3000) / 3000f
                        drawCircle(Color.Yellow, radius = 5f, center = Offset(size.width * flow, y))
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OrganelleChip("Xylem", tissueType == "Xylem", Modifier.weight(1f)) {
                tissueType = "Xylem"
            }
            OrganelleChip("Phloem", tissueType == "Phloem", Modifier.weight(1f)) {
                tissueType = "Phloem"
            }
        }
    }
}

@Composable
fun NutritionInteraction(accent: Color) {
    var glucoseLevel by remember { mutableFloatStateOf(50f) }
    Column {
        Text("Metabolism: Glucose", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(120.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.3f))) {
            Canvas(Modifier.fillMaxSize()) {
                val count = (glucoseLevel / 5f).toInt()
                for(i in 0 until count) {
                    val x = (i * 30f) % size.width
                    val y = (i * 15f) % size.height
                    drawCircle(BioLime, radius = 6f, center = Offset(x, y))
                }
                drawRect(PowerRed.copy(alpha = 0.2f), Offset(size.width - 80f, 20f), Size(60f, 80f))
            }
        }
        Slider(value = glucoseLevel, onValueChange = { glucoseLevel = it }, valueRange = 10f..100f, colors = SliderDefaults.colors(thumbColor = accent, activeTrackColor = accent))
    }
}

@Composable
fun RespirationInteraction(accent: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "resp")
    val expansion by infiniteTransition.animateFloat(
        initialValue = 0.8f, targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Reverse),
        label = "expand"
    )
    Column {
        Text("Cellular Respiration", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(160.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                drawCircle(accent.copy(alpha = 0.2f * expansion), radius = 80f * expansion, center = center)
                drawCircle(accent, radius = 40f * expansion, center = center, style = Stroke(4f))
                for(i in 0..8) {
                    val angle = (i * 45f + expansion * 100f).toDouble()
                    val dist = 100f * expansion
                    drawCircle(NeonCyan, radius = 5f, center = Offset(center.x + dist * cos(Math.toRadians(angle)).toFloat(), center.y + dist * sin(Math.toRadians(angle)).toFloat()))
                }
            }
        }
    }
}

@Composable
fun PlantReproductionInteraction(accent: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "pollination")
    val pollenPos by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = FastOutSlowInEasing), RepeatMode.Restart),
        label = "pollen"
    )
    Column {
        Text("Plant Reproduction", color = GhostWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Box(Modifier.height(160.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Canvas(Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2f, size.height / 2f)
                drawCircle(accent.copy(alpha = 0.2f), radius = 50f, center = center)
                drawRect(accent, Offset(center.x - 5f, center.y), Size(10f, 60f))
                val px = 20f + (center.x - 20f) * pollenPos
                val py = 40f + (center.y - 30f - 40f) * pollenPos - sin(pollenPos * kotlin.math.PI.toFloat()) * 50f
                drawCircle(Color.Yellow, radius = 6f, center = Offset(px, py))
            }
        }
    }
}

@Composable
fun OrganelleChip(name: String, isSelected: Boolean, onClick1: Modifier, onClick: (String) -> Unit) {
    FilterChip(
        selected = isSelected,
        onClick = { onClick(if(isSelected) "None" else name) },
        label = { Text(name, fontSize = 10.sp) },
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = TechViolet.copy(alpha = 0.2f), selectedLabelColor = TechViolet)
    )
}

@Composable
fun DefaultPlaceholder(topic: TopicDetail, accent: Color) {
    Box(Modifier.height(150.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FlaskIcon(color = accent, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(8.dp))
            Text("Interactive Module: ${topic.title}", color = GhostWhite.copy(alpha = 0.5f), fontSize = 12.sp)
        }
    }
}