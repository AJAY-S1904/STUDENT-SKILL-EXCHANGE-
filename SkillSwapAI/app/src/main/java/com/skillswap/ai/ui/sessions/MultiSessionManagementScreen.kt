package com.skillswap.ai.ui.sessions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.requests.RequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSessionManagementScreen(
    meetingId: String,
    onNavigateBack: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sessions = uiState.learningSessionsForMeeting

    LaunchedEffect(meetingId) {
        viewModel.fetchLearningSessionsForMeeting(meetingId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Session Management") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            Text("Overall Progress", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            
            val completedSessions = sessions.count { it.status == com.skillswap.ai.data.model.LearningSessionStatus.COMPLETED }
            val progress = if (sessions.isNotEmpty()) completedSessions.toFloat() / sessions.size else 0f
            
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("${(progress * 100).toInt()}% Completed ($completedSessions/${sessions.size} sessions)")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Learning Sessions", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            
            if (sessions.isEmpty()) {
                Text("No sessions added yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(sessions) { session ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(session.title, style = MaterialTheme.typography.titleSmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Date: ${session.date} | Time: ${session.time}", style = MaterialTheme.typography.bodySmall)
                                Text("Status: ${session.status}", style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
