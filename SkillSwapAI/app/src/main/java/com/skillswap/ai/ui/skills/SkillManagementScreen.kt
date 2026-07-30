package com.skillswap.ai.ui.skills

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.components.GradientBanner
import com.skillswap.ai.ui.components.SkillChip
import com.skillswap.ai.ui.theme.*

val commonSkills = listOf(
    "Python", "Java", "Kotlin", "JavaScript", "React", "Flutter", "Machine Learning",
    "Data Science", "Photoshop", "Figma", "Video Editing", "Public Speaking",
    "Photography", "Guitar", "Piano", "Content Writing", "SEO", "Digital Marketing",
    "UI/UX Design", "Android Dev", "iOS Dev", "Web Design", "C++", "Rust",
    "DevOps", "Docker", "SQL", "Firebase", "Unity", "Blender"
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SkillManagementScreen(
    onNavigateBack: () -> Unit,
    viewModel: SkillViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var teachInput by remember { mutableStateOf("") }
    var learnInput by remember { mutableStateOf("") }
    var showTeachSuggestions by remember { mutableStateOf(false) }
    var showLearnSuggestions by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        item {
            GradientBanner {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                    }
                    Column {
                        Text("Skill Management", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Add and manage your skills", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.8f))
                    }
                }
            }
        }

        item {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // ── Teaching Skills ────────────────────────────────────────
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Column {
                                Text("📚 Skills I Can Teach", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Blue30)
                                Text("${uiState.teachSkills.size} skills added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                            }
                        }

                        OutlinedTextField(
                            value = teachInput,
                            onValueChange = {
                                teachInput = it
                                showTeachSuggestions = it.length >= 2
                            },
                            label = { Text("Add a skill you can teach") },
                            leadingIcon = { Icon(Icons.Filled.Add, null, tint = Blue40) },
                            trailingIcon = {
                                if (teachInput.isNotBlank()) {
                                    IconButton(onClick = {
                                        viewModel.addTeachSkill(teachInput)
                                        teachInput = ""
                                        showTeachSuggestions = false
                                    }) { Icon(Icons.Filled.Check, null, tint = Blue40) }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                viewModel.addTeachSkill(teachInput)
                                teachInput = ""
                                showTeachSuggestions = false
                            }),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Blue40)
                        )

                        // Autocomplete suggestions
                        AnimatedVisibility(showTeachSuggestions) {
                            val suggestions = commonSkills.filter {
                                it.contains(teachInput, ignoreCase = true) &&
                                        !uiState.teachSkills.contains(it)
                            }.take(6)
                            if (suggestions.isNotEmpty()) {
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    suggestions.forEach { skill ->
                                        Surface(
                                            shape = RoundedCornerShape(50),
                                            color = Blue90,
                                            modifier = Modifier
                                                .padding(vertical = 2.dp)
                                                .clickable {
                                                    viewModel.addTeachSkill(skill)
                                                    teachInput = ""
                                                    showTeachSuggestions = false
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(Icons.Filled.Add, null, tint = Blue30, modifier = Modifier.size(14.dp))
                                                Text(skill, style = MaterialTheme.typography.labelMedium, color = Blue30)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (uiState.teachSkills.isEmpty()) {
                            Text("No teaching skills added yet.", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                        } else {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                uiState.teachSkills.forEach { skill ->
                                    SkillChip(skill = skill, isTeach = true, onDelete = { viewModel.removeTeachSkill(skill) })
                                }
                            }
                        }
                    }
                }

                // ── Learning Skills ────────────────────────────────────────
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Column {
                                Text("🎓 Skills I Want to Learn", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Purple30)
                                Text("${uiState.learnSkills.size} skills added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                            }
                        }

                        OutlinedTextField(
                            value = learnInput,
                            onValueChange = {
                                learnInput = it
                                showLearnSuggestions = it.length >= 2
                            },
                            label = { Text("Add a skill you want to learn") },
                            leadingIcon = { Icon(Icons.Filled.Add, null, tint = Purple40) },
                            trailingIcon = {
                                if (learnInput.isNotBlank()) {
                                    IconButton(onClick = {
                                        viewModel.addLearnSkill(learnInput)
                                        learnInput = ""
                                        showLearnSuggestions = false
                                    }) { Icon(Icons.Filled.Check, null, tint = Purple40) }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                viewModel.addLearnSkill(learnInput)
                                learnInput = ""
                                showLearnSuggestions = false
                            }),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                        )

                        // Autocomplete suggestions for learn
                        AnimatedVisibility(showLearnSuggestions) {
                            val suggestions = commonSkills.filter {
                                it.contains(learnInput, ignoreCase = true) &&
                                        !uiState.learnSkills.contains(it)
                            }.take(6)
                            if (suggestions.isNotEmpty()) {
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    suggestions.forEach { skill ->
                                        Surface(
                                            shape = RoundedCornerShape(50),
                                            color = Purple90,
                                            modifier = Modifier
                                                .padding(vertical = 2.dp)
                                                .clickable {
                                                    viewModel.addLearnSkill(skill)
                                                    learnInput = ""
                                                    showLearnSuggestions = false
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(Icons.Filled.Add, null, tint = Purple30, modifier = Modifier.size(14.dp))
                                                Text(skill, style = MaterialTheme.typography.labelMedium, color = Purple30)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (uiState.learnSkills.isEmpty()) {
                            Text("No learning skills added yet.", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                        } else {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                uiState.learnSkills.forEach { skill ->
                                    SkillChip(skill = skill, isTeach = false, onDelete = { viewModel.removeLearnSkill(skill) })
                                }
                            }
                        }
                    }
                }

                // Popular skills quick-add panel
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("💡 Quick Add Popular Skills", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            commonSkills.take(15).forEach { skill ->
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                ) {
                                    Text(
                                        skill,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(80.dp)) }
    }
}
