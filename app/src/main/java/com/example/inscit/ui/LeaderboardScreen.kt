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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.net.Uri
import com.example.inscit.*
import com.example.inscit.models.UserDocument
import com.example.inscit.xp.Rank

@Composable
fun LeaderboardScreen(onBack: () -> Unit) {
    // Since we removed Firebase, the leaderboard will show "No explorers yet" 
    // or we could show a static list. For now, we'll keep it simple as 
    // we don't have a local database for other users yet.
    val rankings by remember { mutableStateOf<List<UserDocument>>(emptyList()) }
    val isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(DeepSpace),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Leaderboard", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = NeonCyan, modifier = Modifier.weight(1f))
                IconButton(onClick = onBack, modifier = Modifier.background(GhostWhite.copy(alpha = 0.1f), CircleShape)) {
                    Text("✕", color = GhostWhite)
                }
            }
            Spacer(Modifier.height(24.dp))
        }

        if (isLoading) {
            item {
                Box(modifier = Modifier.fillParentMaxHeight(0.8f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = NeonCyan)
                }
            }
        } else if (rankings.isEmpty()) {
            item {
                Box(modifier = Modifier.fillParentMaxHeight(0.8f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("No explorers yet", color = GhostWhite.copy(alpha = 0.5f), fontSize = 18.sp)
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

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardBg),
                    shape = RoundedCornerShape(16.dp),
                    border = if (index < 3) BorderStroke(1.dp, rankColor.copy(alpha = 0.5f)) else null
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            color = rankColor,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.width(32.dp)
                        )

                        ProfileImage(
                            photoUrl = doc.profile.photoUrl?.let { try { Uri.parse(it) } catch(e: Exception) { null } },
                            modifier = Modifier.size(44.dp).border(1.dp, rankColor.copy(alpha = 0.3f), CircleShape),
                            placeholderColor = rankColor
                        )

                        Spacer(Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(doc.profile.name, fontWeight = FontWeight.Bold, color = GhostWhite, fontSize = 16.sp)
                            Text("${rank.label} ${rank.icon}", fontSize = 12.sp, color = GhostWhite.copy(alpha = 0.5f))
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("${doc.stats.xp}", fontWeight = FontWeight.ExtraBold, color = NeonCyan, fontSize = 18.sp)
                            Text("XP", fontSize = 10.sp, color = NeonCyan.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }
    }
}
