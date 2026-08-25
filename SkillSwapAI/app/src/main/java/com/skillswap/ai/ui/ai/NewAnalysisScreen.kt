package com.skillswap.ai.ui.ai

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAnalysisScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHistory: () -> Unit,
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val currentAnalysis by viewModel.currentAnalysis.collectAsState()
    val error by viewModel.error.collectAsState()

    var currentSkills by remember { mutableStateOf("") }
    var targetSkill by remember { mutableStateOf("") }
    var careerGoal by remember { mutableStateOf("") }
    var hasStartedGeneration by remember { mutableStateOf(false) }

    // Navigate when generation is successfully completed
    LaunchedEffect(currentAnalysis, isLoading, hasStartedGeneration) {
        if (hasStartedGeneration && !isLoading && currentAnalysis != null) {
            hasStartedGeneration = false // prevent re-triggering
            onNavigateToHistory()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.clearError()
        viewModel.loadAnalysis(null) // clear previous
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New AI Career Analysis") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let's analyze your skills and build a learning roadmap.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = currentSkills,
                onValueChange = { currentSkills = it },
                label = { Text("Current Skills (comma separated)") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = targetSkill,
                onValueChange = { targetSkill = it },
                label = { Text("Target Skill to Learn") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = careerGoal,
                onValueChange = { careerGoal = it },
                label = { Text("Career Goal") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            )
            Spacer(modifier = Modifier.height(32.dp))

            val isAuthenticated = viewModel.currentUserId.isNotEmpty()

            if (!isAuthenticated) {
                Text(
                    text = "⚠️ You must be logged in to use this feature.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    val skillsList = currentSkills.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    viewModel.generateFullCareerAnalysis(careerGoal, targetSkill, skillsList)
                    hasStartedGeneration = true
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !isLoading && isAuthenticated && currentSkills.isNotBlank() && targetSkill.isNotBlank() && careerGoal.isNotBlank()
            ) {
                if (isLoading) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Generating Analysis & Roadmap...")
                    }
                } else {
                    Text("Generate")
                }
            }

            error?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
