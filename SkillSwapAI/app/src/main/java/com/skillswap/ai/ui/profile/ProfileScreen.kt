package com.skillswap.ai.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    onNavigateToSkills: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val user = uiState.user
    var isEditing by remember { mutableStateOf(false) }

    var editName by remember(user?.name) { mutableStateOf(user?.name ?: "") }
    var editBio by remember(user?.bio) { mutableStateOf(user?.bio ?: "") }
    var editDepartment by remember(user?.department) { mutableStateOf(user?.department ?: "") }
    var editYear by remember(user?.year) { mutableStateOf(user?.year ?: "") }
    var editCollege by remember(user?.college) { mutableStateOf(user?.college ?: "") }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.uploadProfilePicture(it) }
    }

    // Animations
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }
    
    val infiniteTransition = rememberInfiniteTransition()
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "pulse"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(600))
            ) {
            // Header Banner
            GradientBanner(modifier = Modifier.fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            "My Profile",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            IconButton(onClick = { isEditing = !isEditing }) {
                                Icon(
                                    if (isEditing) Icons.Filled.Close else Icons.Filled.Edit,
                                    null, tint = Color.White
                                )
                            }
                            IconButton(onClick = onLogout) {
                                Icon(Icons.Filled.Logout, null, tint = Color.White)
                            }
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // Profile Picture
                    Box(
                        contentAlignment = Alignment.BottomEnd,
                        modifier = Modifier.clickable { imagePickerLauncher.launch("image/*") }
                    ) {
                        ProfilePhoto(
                            url = user?.profilePictureUrl ?: "",
                            contentDescription = null,
                            size = 96.dp
                        )
                        Surface(
                            shape = CircleShape, 
                            color = Blue40, 
                            modifier = Modifier
                                .size(28.dp)
                                .graphicsLayer {
                                    scaleX = pulseScale
                                    scaleY = pulseScale
                                }
                        ) {
                            Icon(Icons.Filled.CameraAlt, null, tint = Color.White, modifier = Modifier.padding(5.dp))
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(user?.name ?: "", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    Text("${user?.studentId ?: ""} • ${user?.college ?: ""}", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.8f))

                    Spacer(Modifier.height(12.dp))

                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStat("⭐", String.format("%.1f", user?.rating ?: 0.0), "Rating")
                        ProfileStat("💎", "${user?.skillCredits ?: 0}", "Credits")
                        ProfileStat("📚", "${user?.teachSkills?.size ?: 0}", "Teaching")
                        ProfileStat("🎓", "${user?.learnSkills?.size ?: 0}", "Learning")
                    }
                }
            }
            }
        }

        item {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // Edit Mode
                AnimatedVisibility(visible = isEditing) {
                    Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text("Edit Profile", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            OutlinedTextField(
                                value = editName, onValueChange = { editName = it },
                                label = { Text("Full Name") },
                                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
                            )
                            OutlinedTextField(
                                value = editCollege, onValueChange = { editCollege = it },
                                label = { Text("College") },
                                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
                            )
                            OutlinedTextField(
                                value = editDepartment, onValueChange = { editDepartment = it },
                                label = { Text("Department") },
                                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
                            )
                            OutlinedTextField(
                                value = editYear, onValueChange = { editYear = it },
                                label = { Text("Year (e.g. 2nd Year)") },
                                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
                            )
                            OutlinedTextField(
                                value = editBio, onValueChange = { editBio = it },
                                label = { Text("Bio") },
                                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), minLines = 3, maxLines = 5
                            )
                            Button(
                                onClick = {
                                    viewModel.updateProfile(
                                        name = editName, college = editCollege,
                                        department = editDepartment, year = editYear, bio = editBio
                                    )
                                    isEditing = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) { Text("Save Changes") }
                        }
                    }
                }

                // Info Card
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(initialOffsetY = { 200 }) + fadeIn(tween(800))
                ) {
                ProfileInfoCard(
                    email = user?.email ?: "",
                    college = user?.college ?: "",
                    department = user?.department ?: "",
                    year = user?.year ?: "",
                    bio = user?.bio ?: "",
                    availability = user?.availability ?: emptyList(),
                    experience = user?.experienceLevel ?: ""
                )
                }

                // Skills
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(initialOffsetY = { 400 }) + fadeIn(tween(1000))
                ) {
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("My Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            TextButton(onClick = onNavigateToSkills) { Text("Manage", color = Blue40) }
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("📚 I Can Teach", style = MaterialTheme.typography.labelMedium, color = Blue40, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(6.dp))
                        if (user?.teachSkills.isNullOrEmpty()) {
                            Text("No teaching skills added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                        } else {
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                user?.teachSkills?.forEach { SkillChip(it, isTeach = true) }
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        Text("🎓 I Want to Learn", style = MaterialTheme.typography.labelMedium, color = Purple40, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(6.dp))
                        if (user?.learnSkills.isNullOrEmpty()) {
                            Text("No learning skills added", style = MaterialTheme.typography.bodySmall, color = Neutral20)
                        } else {
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                user?.learnSkills?.forEach { SkillChip(it, isTeach = false) }
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

@Composable
fun ProfileStat(emoji: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji, fontSize = 20.sp)
        Text(value, style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.8f))
    }
}

@Composable
fun ProfileInfoCard(
    email: String, college: String, department: String,
    year: String, bio: String, availability: List<String>, experience: String
) {
    Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("About Me", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (bio.isNotEmpty()) Text(bio, style = MaterialTheme.typography.bodyMedium, color = Neutral20)
            Divider(color = Neutral90)
            InfoRow(Icons.Filled.Email, "Email", email)
            InfoRow(Icons.Filled.School, "College", college)
            InfoRow(Icons.Filled.Business, "Department", department)
            InfoRow(Icons.Filled.CalendarToday, "Year", year)
            InfoRow(Icons.Filled.Psychology, "Experience", experience)
            if (availability.isNotEmpty()) {
                InfoRow(Icons.Filled.AccessTime, "Available", availability.joinToString(", "))
            }
        }
    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    if (value.isBlank()) return
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(icon, null, tint = Blue40, modifier = Modifier.size(18.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = Neutral20)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }
    }
}
