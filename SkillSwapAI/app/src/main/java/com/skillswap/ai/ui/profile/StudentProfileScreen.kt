package com.skillswap.ai.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.skillswap.ai.ui.components.ProfilePhoto
import com.skillswap.ai.ui.components.SkillChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentProfileScreen(
    onNavigateBack: () -> Unit,
    viewModel: StudentProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (uiState.isLoading) "Loading..." else uiState.student?.name ?: "Profile") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.student != null) {
            val student = uiState.student!!
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(Modifier.height(20.dp)) }
                item {
                    ProfilePhoto(
                        url = student.profilePictureUrl,
                        contentDescription = null,
                        size = 120.dp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(student.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("${student.studentId} • ${student.department} • ${student.college}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Spacer(Modifier.height(16.dp))
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoChip("⚡ ${student.experienceLevel}")
                        InfoChip("⭐ ${String.format("%.1f", student.rating)}")
                    }
                    Spacer(Modifier.height(24.dp))
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text("Bio", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(student.bio.ifEmpty { "No bio provided." }, style = MaterialTheme.typography.bodyMedium)
                            
                            HorizontalDivider()

                            Text("📚 Can Teach", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            if (student.teachSkills.isEmpty()) {
                                Text("None added", style = MaterialTheme.typography.bodyMedium)
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    student.teachSkills.forEach { SkillChip(it, isTeach = true) }
                                }
                            }

                            HorizontalDivider()

                            Text("🎓 Wants to Learn", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            if (student.learnSkills.isEmpty()) {
                                Text("None added", style = MaterialTheme.typography.bodyMedium)
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    student.learnSkills.forEach { SkillChip(it, isTeach = false) }
                                }
                            }
                        }
                    }
                }
                item { Spacer(Modifier.height(30.dp)) }
            }
        } else {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Error: ${uiState.error ?: "Student not found"}", color = Color.Red)
            }
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
