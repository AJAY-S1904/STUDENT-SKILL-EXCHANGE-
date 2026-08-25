package com.skillswap.ai.ui.ai

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McqTestScreen(
    initialSkill: String? = null,
    onNavigateBack: () -> Unit,
    viewModel: AiFeaturesViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val test by viewModel.mcqTest.collectAsState()
    val error by viewModel.error.collectAsState()

    var targetSkill by remember { mutableStateOf(initialSkill ?: "") }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var testCompleted by remember { mutableStateOf(false) }
    var testSubmitted by remember { mutableStateOf(false) }
    
    // Timer state
    var timeLeft by remember { mutableIntStateOf(1200) } // 20 minutes in seconds
    var timerRunning by remember { mutableStateOf(false) }
    
    // Store user's selected answers
    val userAnswers = remember { mutableStateMapOf<Int, Int>() }

    // Auto-generate test if initialSkill is provided
    LaunchedEffect(initialSkill) {
        if (!initialSkill.isNullOrBlank()) {
            viewModel.generateMcqTest(initialSkill)
        }
    }
    
    // Timer logic
    LaunchedEffect(timerRunning) {
        if (timerRunning) {
            while (timeLeft > 0 && !testCompleted) {
                delay(1000L)
                timeLeft--
            }
            if (timeLeft <= 0 && !testCompleted) {
                testCompleted = true
                timerRunning = false
            }
        }
    }
    
    // Start timer when test is loaded
    LaunchedEffect(test) {
        if (test != null && !testCompleted) {
            timerRunning = true
            timeLeft = 1200
        }
    }

    // Submit logic
    LaunchedEffect(testCompleted) {
        if (testCompleted && !testSubmitted && test != null) {
            testSubmitted = true
            timerRunning = false
            var calculatedScore = 0
            test?.questions?.forEachIndexed { index, question ->
                if (userAnswers[index] == question.correctAnswerIndex) {
                    calculatedScore++
                }
            }
            score = calculatedScore
            viewModel.submitMcqTest(targetSkill, calculatedScore, test?.questions?.size ?: 0)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MCQ Skill Test") },
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
            if (test == null && !testCompleted) {
                OutlinedTextField(
                    value = targetSkill,
                    onValueChange = { targetSkill = it },
                    label = { Text("Skill to Test") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.generateMcqTest(targetSkill) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading && targetSkill.isNotBlank()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Generate Test")
                    }
                }
            } else if (!testCompleted) {
                val totalQs = test?.questions?.size ?: 1
                
                // Header: Timer and Progress
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(
                        text = "Question ${currentQuestionIndex + 1}/$totalQs",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
                        style = MaterialTheme.typography.titleMedium,
                        color = if (timeLeft < 60) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { (currentQuestionIndex + 1).toFloat() / totalQs.toFloat() },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                
                test?.questions?.getOrNull(currentQuestionIndex)?.let { question ->
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    question.options.forEachIndexed { index, option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = userAnswers[currentQuestionIndex] == index,
                                onClick = { userAnswers[currentQuestionIndex] = index }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = option)
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { if (currentQuestionIndex > 0) currentQuestionIndex-- },
                            enabled = currentQuestionIndex > 0
                        ) {
                            Text("Previous")
                        }
                        
                        Button(
                            onClick = {
                                if (currentQuestionIndex < totalQs - 1) {
                                    currentQuestionIndex++
                                } else {
                                    testCompleted = true
                                    timerRunning = false
                                }
                            },
                            enabled = userAnswers.containsKey(currentQuestionIndex)
                        ) {
                            Text(if (currentQuestionIndex < totalQs - 1) "Next" else "Submit Test")
                        }
                    }
                }
            } else {
                val totalQuestions = test?.questions?.size ?: 0
                val percentage = if (totalQuestions > 0) (score.toFloat() / totalQuestions) * 100 else 0f
                val passed = percentage >= 70f
                
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Saving results...", modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally))
                } else {
                    Text(
                        text = if (passed) "🎉 Congratulations!" else "Skill Not Verified",
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (passed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Score: $score / $totalQuestions (${percentage.toInt()}%)",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    if (passed) {
                        Text(
                            text = "Your skill '$targetSkill' has been verified and added to your teaching skills.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = onNavigateBack,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Done")
                        }
                    } else {
                        Text(
                            text = "You need at least 70% to teach this skill.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = {
                                testCompleted = false
                                testSubmitted = false
                                currentQuestionIndex = 0
                                userAnswers.clear()
                                score = 0
                                timeLeft = 1200
                                viewModel.generateMcqTest(targetSkill)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Retake Test")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = { onNavigateBack() }, // Navigates back, user can go to Tools -> Learning Roadmap
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Learning Roadmap")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancel")
                        }
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
