package com.skillswap.ai.ui.ai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skillswap.ai.data.model.LearningRoadmap
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningRoadmapHistoryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToRoadmap: (String) -> Unit,
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val history by viewModel.roadmapHistory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var showDeleteDialogFor by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.clearError()
        viewModel.loadRoadmapHistory()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learning Roadmaps") },
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
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Manage your previous learning roadmaps.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading && history.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (history.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No previous roadmaps found.", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(history, key = { it.id }) { roadmap ->
                        LearningRoadmapCard(
                            roadmap = roadmap,
                            onViewRoadmap = { onNavigateToRoadmap(roadmap.analysisId) },
                            onDelete = { showDeleteDialogFor = roadmap.id }
                        )
                    }
                }
            }
        }

        if (showDeleteDialogFor != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialogFor = null },
                title = { Text("Delete Roadmap") },
                text = { Text("Are you sure you want to delete this roadmap? This action cannot be undone.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteRoadmap(showDeleteDialogFor!!)
                            showDeleteDialogFor = null
                        }
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialogFor = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun LearningRoadmapCard(
    roadmap: LearningRoadmap,
    onViewRoadmap: () -> Unit,
    onDelete: () -> Unit
) {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val dateStr = sdf.format(Date(roadmap.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(roadmap.careerGoal, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Target Skill: ${roadmap.targetSkill}", style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Difficulty", style = MaterialTheme.typography.labelSmall)
                    Text(roadmap.difficultyLevel, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
                }
                Column {
                    Text("Duration", style = MaterialTheme.typography.labelSmall)
                    Text("${roadmap.overallEstimatedWeeks} Weeks", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Date", style = MaterialTheme.typography.labelSmall)
                    Text(dateStr, style = MaterialTheme.typography.labelMedium)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onViewRoadmap,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Roadmap")
            }
        }
    }
}
