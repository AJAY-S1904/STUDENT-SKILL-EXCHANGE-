package com.skillswap.ai.ui.credits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.data.model.CreditType
import com.skillswap.ai.data.model.SkillCredit
import com.skillswap.ai.ui.components.EmptyState
import com.skillswap.ai.ui.components.GradientBanner
import com.skillswap.ai.ui.components.SectionHeader
import com.skillswap.ai.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CreditsScreen(
    viewModel: CreditViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        item {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(GradientStart, GradientMid),
                            start = Offset(0f, 0f),
                            end = Offset(600f, 400f)
                        )
                    )
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("💎 Skill Credits", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(20.dp))

                    // Balance Card
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f))
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Your Balance", style = MaterialTheme.typography.labelLarge, color = Color.White.copy(0.8f))
                            Spacer(Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("⭐", fontSize = 32.sp)
                                Text(
                                    "${uiState.balance}",
                                    style = MaterialTheme.typography.displayMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text("Credits", style = MaterialTheme.typography.bodyLarge, color = Color.White.copy(0.8f))
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // How credits work
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CreditInfoChip("📚 Teach", "+3 Credits", AcceptedColor)
                        CreditInfoChip("🎓 Learn", "-1 Credit", PendingColor)
                        CreditInfoChip("🎁 Bonus", "+10 Join", Blue40)
                    }
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                SectionHeader(title = "📜 Credit History")
            }
        }

        if (uiState.history.isEmpty()) {
            item {
                EmptyState(
                    emoji = "💎",
                    title = "No credit history",
                    subtitle = "Start teaching to earn your first credits!"
                )
            }
        } else {
            items(uiState.history, key = { it.id }) { credit ->
                CreditHistoryItem(credit = credit)
            }
        }

        item { Spacer(Modifier.height(80.dp)) }
    }
}

@Composable
fun CreditHistoryItem(credit: SkillCredit) {
    val isPositive = credit.amount > 0
    val bgColor = if (isPositive) AcceptedColor.copy(0.1f) else PendingColor.copy(0.1f)
    val textColor = if (isPositive) AcceptedColor else CancelledColor
    val emoji = when (credit.type) {
        CreditType.EARNED_TEACHING.name -> "📚"
        CreditType.SPENT_LEARNING.name  -> "🎓"
        CreditType.BONUS.name           -> "🎁"
        CreditType.REFUND.name          -> "↩️"
        else                            -> "💎"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 20.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(credit.description.ifEmpty { credit.type }, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                if (credit.createdAt > 0) {
                    Text(
                        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(java.util.Date(credit.createdAt)),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                    )
                }
            }
            Text(
                "${if (isPositive) "+" else ""}${credit.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
fun CreditInfoChip(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.8f))
        Text(value, style = MaterialTheme.typography.labelLarge, color = Color.White, fontWeight = FontWeight.Bold)
    }
}
