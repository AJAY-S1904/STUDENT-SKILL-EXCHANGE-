package com.skillswap.ai.ui.matching
// Force recompile

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.skillswap.ai.data.model.AiMatchResponse
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiMatchingScreen(
    onSendRequest: (String) -> Unit,
    viewModel: AiMatchingViewModel = hiltViewModel(),
    requestViewModel: com.skillswap.ai.ui.requests.RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showRequestDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Header
            GradientBanner(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        "🤖 AI Skill Matching",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Find your perfect skill exchange partner",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(0.8f)
                    )
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        // Current profile summary
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("📋 Your Matching Profile", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Divider(color = Neutral90)

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("📚 I Can Teach", style = MaterialTheme.typography.labelSmall, color = Blue40, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.height(4.dp))
                            if (uiState.teachSkills.isEmpty()) {
                                Text("None added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    uiState.teachSkills.take(4).forEach {
                                        SkillChip(it, isTeach = true)
                                    }
                                }
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("🎓 Want to Learn", style = MaterialTheme.typography.labelSmall, color = Purple40, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.height(4.dp))
                            if (uiState.learnSkills.isEmpty()) {
                                Text("None added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    uiState.learnSkills.take(4).forEach {
                                        SkillChip(it, isTeach = false)
                                    }
                                }
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoChip("⚡ ${uiState.experience}")
                        InfoChip("⭐ ${String.format("%.1f", uiState.rating)} rating")
                        if (uiState.availability.isNotEmpty()) {
                            InfoChip("📅 ${uiState.availability.size} days/wk")
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        // Error
        if (uiState.error != null) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(Red90),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Error, null, tint = Red40)
                        Text(uiState.error ?: "", style = MaterialTheme.typography.bodySmall, color = Red40)
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }

        // Find Match Button
        item {
            AnimatedVisibility(visible = !uiState.hasMatched) {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        onClick = { viewModel.findBestMatch() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp),
                        enabled = !uiState.isLoading && uiState.teachSkills.isNotEmpty() && uiState.learnSkills.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    if (uiState.isLoading || uiState.teachSkills.isEmpty() || uiState.learnSkills.isEmpty())
                                        Brush.linearGradient(listOf(Color.Gray, Color.Gray))
                                    else
                                        Brush.linearGradient(
                                            colors = listOf(GradientStart, GradientMid, GradientEnd)
                                        ),
                                    RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (uiState.isLoading) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                                    Text("Finding Best Match...", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("🤖", fontSize = 22.sp)
                                    Text("Find Best Match", color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            if (uiState.teachSkills.isEmpty() || uiState.learnSkills.isEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "⚠️ Add teaching and learning skills in your profile to use AI matching",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        // AI Recommendation Card
        if (uiState.aiResponse != null) {
            item {
                AiRecommendationCard(
                    response = uiState.aiResponse!!,
                    onSendRequest = { showRequestDialog = true },
                    onReset = { viewModel.reset() }
                )
                Spacer(Modifier.height(80.dp))
            }
        }
    }

    if (showRequestDialog && uiState.aiResponse != null) {
        val student = uiState.aiResponse!!.recommended_student
        var message by remember { mutableStateOf("Hi! I'd love to exchange skills with you.") }
        var selectedTeach by remember { mutableStateOf(if (student.learning_skills.isNotEmpty()) student.learning_skills.first() else "") }
        var selectedLearn by remember { mutableStateOf(if (student.teach_skills.isNotEmpty()) student.teach_skills.first() else "") }


        AlertDialog(
            onDismissRequest = { showRequestDialog = false },
            title = { Text("Request ${student.name}") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = selectedTeach,
                        onValueChange = { selectedTeach = it },
                        label = { Text("Skill to Teach") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = selectedLearn,
                        onValueChange = { selectedLearn = it },
                        label = { Text("Skill to Learn") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Message") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    requestViewModel.sendRequest(
                        receiverId = student.uid,
                        receiverName = student.name,
                        receiverProfilePic = "",
                        teachSkill = selectedTeach,
                        learnSkill = selectedLearn,
                        message = message

                    )
                    showRequestDialog = false
                    onSendRequest(student.uid)
                }) {
                    Text("Send Request")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRequestDialog = false }) {
                    Text("Cancel")
                }
            }
        )

    }
}

@Composable
fun AiRecommendationCard(
    response: AiMatchResponse,
    onSendRequest: () -> Unit,
    onReset: () -> Unit
) {
    val student = response.recommended_student
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1200, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "glow"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Purple10.copy(alpha = 0.05f),
                            Blue10.copy(alpha = 0.02f)
                        )
                    )
                )
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // AI Badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(GradientMid, GradientEnd)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🤖", fontSize = 20.sp)
                    }
                    Column {
                        Text(
                            "🤖 AI Recommendation",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Purple30
                        )
                        Text(
                            "Powered by Random Forest AI",
                            style = MaterialTheme.typography.labelSmall,
                            color = Neutral20
                        )
                    }
                }

                Divider(color = Purple90)

                // Student Profile
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(listOf(Blue90, Purple90))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(student.name.first().toString(), fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Blue30)
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(student.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            "${student.department} • ${student.college}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            InfoChip("⚡ ${student.experience}")
                            InfoChip("⭐ ${String.format("%.1f", student.rating)}")
                        }
                    }
                }

                // Match Scores
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ScoreCard(
                        modifier = Modifier.weight(1f),
                        label = "Match Score",
                        value = "${response.match_percentage.toInt()}%",
                        color = AcceptedColor,
                        emoji = "🎯"
                    )
                    ScoreCard(
                        modifier = Modifier.weight(1f),
                        label = "Compatibility",
                        value = "${response.compatibility_score.toInt()}%",
                        color = Blue40,
                        emoji = "💡"
                    )
                }

                // Progress bar
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Match Strength", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
                    LinearProgressIndicator(
                        progress = (response.match_percentage / 100f).toFloat(),
                        modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(50)),
                        color = GradientMid,
                        trackColor = Purple90
                    )
                }

                // Reasons
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Blue90.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Why This Match?", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Blue30)
                        response.reason.forEach { reason ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Blue40)
                                )
                                Text(reason, style = MaterialTheme.typography.bodySmall, color = Blue20)
                            }
                        }
                    }
                }

                // Their skills
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Can Teach You", style = MaterialTheme.typography.labelSmall, color = Blue40, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(4.dp))
                        if (student.teach_skills.isEmpty()) {
                            Text("None specified", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                        } else {
                            student.teach_skills.take(3).forEach { SkillChip(it, isTeach = true) }
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Wants to Learn", style = MaterialTheme.typography.labelSmall, color = Purple40, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(4.dp))
                        if (student.learning_skills.isEmpty()) {
                            Text("None specified", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                        } else {
                            student.learning_skills.take(3).forEach { SkillChip(it, isTeach = false) }
                        }
                    }
                }

                // Action Buttons
                Button(
                    onClick = onSendRequest,
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(listOf(GradientStart, GradientMid)),
                                RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.Send, null, tint = Color.White, modifier = Modifier.size(20.dp))
                            Text("Send Exchange Request", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                TextButton(onClick = onReset, modifier = Modifier.fillMaxWidth()) {
                    Text("🔄 Find Another Match", color = Purple40)
                }
            }
        }
    }
}

@Composable
fun ScoreCard(modifier: Modifier = Modifier, label: String, value: String, color: Color, emoji: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(emoji, fontSize = 24.sp)
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = color)
            Text(label, style = MaterialTheme.typography.labelSmall, color = Neutral20, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium
        )
    }
}
