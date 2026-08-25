package com.skillswap.ai.ui.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SkillPortfolioScreen(
    onNavigateBack: () -> Unit,
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val portfolio by viewModel.portfolio.collectAsState()
    val user by viewModel.user.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPortfolio(viewModel.currentUserId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Skill Portfolio") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                if (user != null && portfolio != null) {
                    val u = user!!
                    val p = portfolio!!
                    
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Top Stats
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                StatCard("Verified Skills", "${p.verifiedSkills.size}", "🏆", Color(0xFFFFB300))
                                StatCard("Skill Level", if (p.verifiedSkills.size > 5) "Expert" else if (p.verifiedSkills.size > 2) "Intermediate" else "Beginner", "⭐", Color(0xFF4CAF50))
                                StatCard("Sessions Done", "${p.totalSessionsCompleted}", "✅", Color(0xFF2196F3))
                            }
                        }

                        // Teaching Skills (Verified Badges)
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Teaching Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    if (u.teachSkills.isEmpty()) {
                                        Text("No teaching skills added yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    } else {
                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            u.teachSkills.forEach { skill ->
                                                val isVerified = p.verifiedSkills.any { it.equals(skill, ignoreCase = true) }
                                                FilterChip(
                                                    selected = isVerified,
                                                    onClick = {},
                                                    label = { Text(skill) },
                                                    leadingIcon = if (isVerified) {
                                                        { Icon(Icons.Default.Verified, contentDescription = "Verified", tint = Color(0xFF4CAF50)) }
                                                    } else null
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        // Learning Skills
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Learning Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    if (u.learnSkills.isEmpty()) {
                                        Text("No learning skills added yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    } else {
                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            u.learnSkills.forEach { skill ->
                                                SuggestionChip(
                                                    onClick = {},
                                                    label = { Text(skill) }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Achievements
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.WorkspacePremium, contentDescription = "Achievements", tint = MaterialTheme.colorScheme.primary)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Achievements", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    
                                    if (p.totalSessionsCompleted >= 10) {
                                        Text("🌟 Master Mentor (10+ Sessions)")
                                    }
                                    if (p.verifiedSkills.size >= 3) {
                                        Text("🎓 Multi-Skilled (3+ Verified Skills)")
                                    }
                                    if (p.totalSessionsCompleted < 10 && p.verifiedSkills.size < 3) {
                                        Text("Keep learning and teaching to unlock achievements!", color = MaterialTheme.colorScheme.onPrimaryContainer)
                                    }
                                }
                            }
                        }
                        
                        item { Spacer(modifier = Modifier.height(32.dp)) }
                    }
                }
            }

            error?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, icon: String, iconColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.width(100.dp).height(100.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(icon, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
