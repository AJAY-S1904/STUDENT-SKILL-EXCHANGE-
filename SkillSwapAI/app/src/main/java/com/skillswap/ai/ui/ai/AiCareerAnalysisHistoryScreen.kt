package com.skillswap.ai.ui.ai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skillswap.ai.data.model.AiCareerAnalysis
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiCareerAnalysisHistoryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewAnalysis: () -> Unit,
    onNavigateToAnalysis: (String) -> Unit,
    onNavigateToRoadmap: (String) -> Unit,
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val history by viewModel.analysisHistory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf(SortOption.NEWEST_FIRST) }
    var showSortMenu by remember { mutableStateOf(false) }
    var showDeleteDialogFor by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.clearError()
        viewModel.loadAnalysisHistory()
    }

    val filteredHistory = history.filter {
        it.careerGoal.contains(searchQuery, ignoreCase = true) || 
        it.targetSkill.contains(searchQuery, ignoreCase = true)
    }.let { list ->
        when (sortOption) {
            SortOption.NEWEST_FIRST -> list.sortedByDescending { it.createdAt }
            SortOption.OLDEST_FIRST -> list.sortedBy { it.createdAt }
            SortOption.HIGHEST_READINESS -> list.sortedByDescending { it.overallReadiness }
            SortOption.LOWEST_SKILL_GAP -> list.sortedBy { it.skillGap }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Career Analysis") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            if (history.isNotEmpty()) {
                FloatingActionButton(
                    onClick = onNavigateToNewAnalysis,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "New Analysis")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Manage your previous AI analyses and learning roadmaps.",
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No previous AI analyses found.", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onNavigateToNewAnalysis) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("New Analysis")
                        }
                    }
                }
            } else {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search Career Goal or Target Skill") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${filteredHistory.size} Analyses",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Box {
                        TextButton(onClick = { showSortMenu = true }) {
                            Text("Sort: ${sortOption.displayName}")
                        }
                        DropdownMenu(
                            expanded = showSortMenu,
                            onDismissRequest = { showSortMenu = false }
                        ) {
                            SortOption.values().forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option.displayName) },
                                    onClick = {
                                        sortOption = option
                                        showSortMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(filteredHistory, key = { it.id }) { analysis ->
                        AiCareerAnalysisCard(
                            analysis = analysis,
                            onViewAnalysis = { onNavigateToAnalysis(analysis.id) },
                            onViewRoadmap = { onNavigateToRoadmap(analysis.id) },
                            onDelete = { showDeleteDialogFor = analysis.id }
                        )
                    }
                }
            }
        }

        if (showDeleteDialogFor != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialogFor = null },
                title = { Text("Delete Analysis") },
                text = { Text("Are you sure you want to delete this analysis? Its associated learning roadmap will also be deleted. This action cannot be undone.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteAnalysis(showDeleteDialogFor!!)
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

enum class SortOption(val displayName: String) {
    NEWEST_FIRST("Newest First"),
    OLDEST_FIRST("Oldest First"),
    HIGHEST_READINESS("Highest Readiness"),
    LOWEST_SKILL_GAP("Lowest Skill Gap")
}

@Composable
fun AiCareerAnalysisCard(
    analysis: AiCareerAnalysis,
    onViewAnalysis: () -> Unit,
    onViewRoadmap: () -> Unit,
    onDelete: () -> Unit
) {
    val sdf = SimpleDateFormat("dd MMM yyyy • hh:mm a", Locale.getDefault())
    val dateStr = sdf.format(Date(analysis.createdAt))

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
                    Text(analysis.careerGoal, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Target Skill: ${analysis.targetSkill}", style = MaterialTheme.typography.bodyMedium)
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
                    Text("Readiness", style = MaterialTheme.typography.labelSmall)
                    Text("${analysis.overallReadiness}%", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
                }
                Column {
                    Text("Skill Gap", style = MaterialTheme.typography.labelSmall)
                    Text("${analysis.skillGap}%", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.error)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Date", style = MaterialTheme.typography.labelSmall)
                    Text(dateStr, style = MaterialTheme.typography.labelMedium)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onViewAnalysis,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("View Analysis")
                }
                Button(
                    onClick = onViewRoadmap,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("View Roadmap")
                }
            }
        }
    }
}
