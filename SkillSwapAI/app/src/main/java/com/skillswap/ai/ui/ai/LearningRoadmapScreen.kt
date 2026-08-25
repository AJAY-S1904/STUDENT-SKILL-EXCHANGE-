package com.skillswap.ai.ui.ai

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningRoadmapScreen(
    analysisId: String,
    onNavigateBack: () -> Unit,
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val analysis by viewModel.currentRoadmap.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var expandedStageIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(analysisId) {
        viewModel.clearError()
        viewModel.loadRoadmapByAnalysisId(analysisId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learning Roadmap") },
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
            if (analysis != null) {
                val roadmap = analysis!!
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Dashboard Header
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text("Learning Dashboard", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.School, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Career Goal:", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(roadmap.careerGoal)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Flag, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Target Skill:", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(roadmap.targetSkill)
                                }
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Timer, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Est. Duration:", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("${roadmap.overallEstimatedWeeks} Weeks")
                                }
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.TrendingUp, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Difficulty Level:", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(roadmap.difficultyLevel)
                                }
                            }
                        }
                    }

                    // Stages
                    val defaultColors = listOf(
                        Color(0xFF4CAF50) to Color(0xFFE8F5E9),
                        Color(0xFFFFC107) to Color(0xFFFFF8E1),
                        Color(0xFFF44336) to Color(0xFFFFEBEE)
                    )

                    roadmap.stages.forEachIndexed { index, stage ->
                        item {
                            val isExpanded = expandedStageIndex == index
                            val colors = defaultColors.getOrElse(index) { Color(0xFF2196F3) to Color(0xFFE3F2FD) }
                            val (iconColor, containerColor) = colors

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedStageIndex = if (isExpanded) -1 else index },
                                colors = CardDefaults.cardColors(containerColor = containerColor),
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(stage.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = iconColor)
                                        Icon(
                                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                            contentDescription = "Toggle Expand",
                                            tint = iconColor
                                        )
                                    }

                                    AnimatedVisibility(visible = isExpanded) {
                                        Column(modifier = Modifier.padding(top = 16.dp)) {
                                            Text(stage.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Spacer(modifier = Modifier.height(8.dp))
                                            
                                            val allTopics = stage.highPriorityTopics + stage.mediumPriorityTopics + stage.advancedTopics
                                            allTopics.forEach { topic ->
                                                Text("• $topic", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp, top = 4.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            } else if (isLoading) {
                Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No roadmap exists for this analysis.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.regenerateRoadmapForAnalysis(analysisId) }) {
                            Text("Generate Roadmap")
                        }
                    }
                }
            }
        }
    }
}
