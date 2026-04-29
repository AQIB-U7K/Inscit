package com.example.inscit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.net.Uri
import com.example.inscit.*
import com.example.inscit.models.UserDocument
import com.example.inscit.xp.Rank

import com.example.inscit.ui.theme.spacing

@Composable
fun LeaderboardScreen(onBack: () -> Unit) {
    val spacing = MaterialTheme.spacing
    val rankings by remember { mutableStateOf<List<UserDocument>>(emptyList()) }
    val isLoading by remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(DeepSpace)) {
        val screenWidth = maxWidth
        val horizontalPadding = if (screenWidth > 600.dp) spacing.huge else spacing.large

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = spacing.large),
            verticalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text("Leaderboard", style = MaterialTheme.typography.headlineMedium, color = NeonCyan, modifier = Modifier.weight(1f))
                    IconButton(onClick = onBack, modifier = Modifier.background(GhostWhite.copy(alpha = 0.05f), CircleShape)) {
                        Text("✕", color = GhostWhite, style = MaterialTheme.typography.titleMedium)
                    }
                }
                Spacer(Modifier.height(spacing.extraLarge))
            }

            if (isLoading) {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight(0.8f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = NeonCyan)
                    }
                }
            } else if (rankings.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight(0.6f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("🔭", fontSize = 64.sp)
                            Spacer(Modifier.height(spacing.medium))
                            Text("No explorers yet", style = MaterialTheme.typography.titleMedium, color = GhostWhite.copy(alpha = 0.5f))
                        }
                    }
                }
            } else {
                itemsIndexed(rankings) { index, doc ->
                    val rankColor = when(index) {
                        0 -> Color(0xFFFFD700)
                        1 -> Color(0xFFC0C0C0)
                        2 -> Color(0xFFCD7F32)
                        else -> NeonCyan.copy(alpha = 0.6f)
                    }

                    val rank = Rank.fromXp(doc.stats.xp)

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = CardBg,
                        shape = RoundedCornerShape(24.dp),
                        border = if (index < 3) BorderStroke(1.dp, rankColor.copy(alpha = 0.3f)) else BorderStroke(1.dp, GhostWhite.copy(alpha = 0.05f))
                    ) {
                        Row(
                            modifier = Modifier.padding(spacing.large),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = (index + 1).toString(),
                                color = rankColor,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.width(40.dp)
                            )

                            ProfileImage(
                                photoUrl = doc.profile.photoUrl?.let { try { Uri.parse(it) } catch(e: Exception) { null } },
                                modifier = Modifier.size(52.dp).clip(CircleShape).border(1.5.dp, rankColor.copy(alpha = 0.5f), CircleShape),
                                placeholderColor = rankColor
                            )

                            Spacer(Modifier.width(spacing.medium))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(doc.profile.name, style = MaterialTheme.typography.titleMedium, color = GhostWhite)
                                Text("${rank.label} ${rank.icon}", style = MaterialTheme.typography.labelSmall, color = GhostWhite.copy(alpha = 0.5f))
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("${doc.stats.xp}", style = MaterialTheme.typography.titleLarge, color = NeonCyan)
                                Text("XP", style = MaterialTheme.typography.labelSmall, color = NeonCyan.copy(alpha = 0.7f))
                            }
                        }
                    }
                }
            }
        }
    }
}
