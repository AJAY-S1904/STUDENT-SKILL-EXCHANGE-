package com.skillswap.ai.ui.ai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SkillGapScreen(
    analysisId: String,
    onNavigateBack: () -> Unit,
    onNavigateToRoadmap: (String) -> Unit = { _ -> },
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val analysis by viewModel.currentAnalysis.collectAsState()

    LaunchedEffect(analysisId) {
        viewModel.clearError()
        viewModel.loadAnalysis(analysisId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Skill Gap Analysis") },
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
            if (analysis == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Analysis not found.")
                }
            } else {
                analysis?.let { result ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // 1. Career Goal
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.TrackChanges, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Career Goal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(result.careerGoal, style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }

                        // 2 & 3. Readiness and Gap
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(contentAlignment = Alignment.Center) {
                                        CircularProgressIndicator(
                                            progress = { result.overallReadiness / 100f },
                                            modifier = Modifier.size(80.dp),
                                            color = Color(0xFF4CAF50),
                                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                        )
                                        Text("${result.overallReadiness}%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Overall Readiness", style = MaterialTheme.typography.bodyMedium)
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(contentAlignment = Alignment.Center) {
                                        CircularProgressIndicator(
                                            progress = { result.skillGap / 100f },
                                            modifier = Modifier.size(80.dp),
                                            color = Color(0xFFF44336),
                                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                        )
                                        Text("${result.skillGap}%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Skill Gap", style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }

                        // 4. Strengths
                        if (result.strengths.isNotEmpty()) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Strengths", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        result.strengths.forEach { strength ->
                                            Text("• $strength", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF1B5E20))
                                        }
                                    }
                                }
                            }
                        }

                        // 5. Weaknesses
                        if (result.weaknesses.isNotEmpty()) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Cancel, contentDescription = null, tint = Color(0xFFF44336))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Weaknesses", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFFC62828))
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        result.weaknesses.forEach { weakness ->
                                            Text("• $weakness", style = MaterialTheme.typography.bodyMedium, color = Color(0xFFB71C1C))
                                        }
                                    }
                                }
                            }
                        }

                        // 6. Missing Skills
                        if (result.missingSkills.isNotEmpty()) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Book, contentDescription = null, tint = Color(0xFFFF9800))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Missing Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFFEF6C00))
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        
                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            result.missingSkills.forEach { skill ->
                                                SuggestionChip(
                                                    onClick = {},
                                                    label = { Text(skill, color = Color(0xFFE65100)) },
                                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                                        containerColor = Color(0xFFFFE0B2)
                                                    ),
                                                    border = null
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // 7. Recommendations
                        if (result.recommendations.isNotBlank()) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5)),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color(0xFF9C27B0))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Recommendations", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF6A1B9A))
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(result.recommendations, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF4A148C))
                                    }
                                }
                            }
                        }

                        // Button
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { onNavigateToRoadmap(result.id) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = MaterialTheme.shapes.medium,
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                Text("View Learning Roadmap", style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }
    }
}
