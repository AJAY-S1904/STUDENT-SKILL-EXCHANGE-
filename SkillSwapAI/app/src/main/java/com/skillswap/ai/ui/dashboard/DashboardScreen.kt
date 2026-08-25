package com.skillswap.ai.ui.dashboard

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.skillswap.ai.data.model.User
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DashboardScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToMatching: () -> Unit,
    onNavigateToStudentProfile: (String) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToSessions: () -> Unit,
    onNavigateToTools: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val user = uiState.currentUser
    val recommendedUsers = viewModel.getAiRecommendedUsers()
    val popularSkills = viewModel.getPopularSkills()

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // ── Welcome Banner ──────────────────────────────────────────────────
        item {
            GradientBanner(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Hello, ${user?.name?.split(" ")?.firstOrNull() ?: "Student"} 👋",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Ready to learn something new today?",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                        // Notification + Avatar
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            BadgedBox(
                                badge = {
                                    if (uiState.unreadNotifications > 0) {
                                        Badge { Text("${uiState.unreadNotifications}") }
                                    }
                                }
                            ) {
                                IconButton(onClick = onNavigateToNotifications) {
                                    Icon(Icons.Filled.Notifications, null, tint = Color.White)
                                }
                            }
                            ProfilePhoto(
                                url = user?.profilePictureUrl ?: "",
                                contentDescription = null,
                                modifier = Modifier.clickable { onNavigateToProfile() },
                                size = 44.dp
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Credit Balance
                    CreditBalanceChip(credits = user?.skillCredits ?: 0)

                    Spacer(Modifier.height(16.dp))

                    // Search Bar
                    Surface(
                        modifier = Modifier.fillMaxWidth().clickable { onNavigateToSearch() },
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White.copy(alpha = 0.95f),
                        tonalElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Filled.Search, null, tint = Blue40)
                            Text(
                                "Search skills, students...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Neutral20
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        // ── Tools Section ──────────────────────────────────────────────────
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onNavigateToTools() },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("🛠️ Tools", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Text("Improve, plan and showcase your skills", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Button(
                            onClick = onNavigateToTools,
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("Explore Tools") }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        // ── AI Matching Banner ─────────────────────────────────────────────
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Purple90
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("🤖 AI Skill Matching", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Purple10)
                            Text("Find your perfect skill partner using AI", style = MaterialTheme.typography.bodySmall, color = Purple20)
                        }
                        Button(
                            onClick = onNavigateToMatching,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                        ) { Text("Match Me!", color = Color.White, fontWeight = FontWeight.Bold) }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        // ── AI Recommended Students ────────────────────────────────────────
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                SectionHeader(
                    title = "✨ Recommended for You",
                    actionText = "See All",
                    onAction = onNavigateToSearch
                )
            }
        }

        item {
            if (uiState.isLoading) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(3) { ShimmerCard() }
                }
            } else if (recommendedUsers.isEmpty()) {
                EmptyState(
                    emoji = "🔍",
                    title = "No recommendations available right now.",
                    subtitle = "Check back later!"
                )
            } else {
                val student = recommendedUsers.first()
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    RecommendedStudentCard(
                        user = student,
                        onClick = { onNavigateToStudentProfile(student.uid) }
                    )
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        // ── Popular Skills ─────────────────────────────────────────────────
        if (popularSkills.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    SectionHeader(title = "🔥 Popular Skills")
                    Spacer(Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        popularSkills.forEach { skill ->
                            SkillChip(skill = skill, isTeach = false)
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        // ── Recent Requests ────────────────────────────────────────────────
        if (uiState.recentRequests.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    SectionHeader(title = "📨 Recent Requests")
                }
            }
            items(uiState.recentRequests) { request ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    val isSender = request.senderId == viewModel.currentUserId
                    val otherUserId = if (isSender) request.receiverId else request.senderId
                    val realTimePhoto = uiState.allUsers.find { it.uid == otherUserId }?.profilePictureUrl
                    
                    com.skillswap.ai.ui.components.RequestCard(
                        request = request,
                        currentUserId = viewModel.currentUserId,
                        otherUserPhoto = realTimePhoto,
                        onViewMeeting = onNavigateToSessions
                    )
                }
            }
        }

        item { Spacer(Modifier.height(80.dp)) }
    }
}

@Composable
fun RecommendedStudentCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfilePhoto(
                url = user.profilePictureUrl,
                contentDescription = user.name,
                size = 72.dp
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(user.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(user.college, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(0.6f), maxLines = 1)
                
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFB300), modifier = Modifier.size(16.dp))
                    Text(String.format("%.1f", user.rating), style = MaterialTheme.typography.labelMedium)
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    user.teachSkills.take(3).forEach { skill ->
                        SkillChip(skill = skill, isTeach = true)
                    }
                }
            }
        }
    }
}
