package com.skillswap.ai.ui.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.skillswap.ai.data.model.Session
import com.skillswap.ai.data.model.SessionStatus
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionsScreen(
    onNavigateToRating: (Session) -> Unit,
    viewModel: SessionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sessions = if (uiState.selectedTab == 0) uiState.learningSessions else uiState.teachingSessions

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        GradientBanner {
            Column {
                Text("Learning Sessions", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                Text(
                    "${uiState.teachingSessions.size} teaching • ${uiState.learningSessions.size} learning",
                    style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.8f)
                )
            }
        }

        TabRow(selectedTabIndex = uiState.selectedTab, contentColor = Blue40) {
            Tab(selected = uiState.selectedTab == 0, onClick = { viewModel.selectTab(0) }, text = { Text("Learning") })
            Tab(selected = uiState.selectedTab == 1, onClick = { viewModel.selectTab(1) }, text = { Text("Teaching") })
        }

        if (sessions.isEmpty()) {
            EmptyState(
                emoji = "📚",
                title = "No sessions yet",
                subtitle = "Accept exchange requests to start sessions"
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(sessions, key = { it.id }) { session ->
                    SessionCard(
                        session = session,
                        currentUserId = viewModel.currentUserId,
                        onMarkComplete = { viewModel.markComplete(session) },
                        onRateSession = { onNavigateToRating(session) },
                        onSchedule = { date, time -> viewModel.scheduleSession(session.id, date, time) }
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionCard(
    session: Session,
    currentUserId: String,
    onMarkComplete: () -> Unit,
    onRateSession: () -> Unit,
    onSchedule: (String, String) -> Unit
) {
    var showNotesDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    val timePickerState = rememberTimePickerState()
    var tempDate by remember { mutableStateOf("") }
    val statusColor = when (session.status) {
        SessionStatus.SCHEDULED.name -> PendingColor
        SessionStatus.ONGOING.name   -> Blue40
        SessionStatus.COMPLETED.name -> AcceptedColor
        SessionStatus.CANCELLED.name -> CancelledColor
        else                         -> PendingColor
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(statusColor)
                    )
                    Text(session.status, style = MaterialTheme.typography.labelMedium, color = statusColor, fontWeight = FontWeight.SemiBold)
                }
                Surface(shape = RoundedCornerShape(50), color = Blue90) {
                    Text(
                        session.skill,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Blue30,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Divider(color = Neutral90)

            // Participants
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ParticipantChip(label = "Teacher", name = session.teacherName, picUrl = session.teacherProfilePic, isTeacher = true)
                Icon(Icons.Filled.ArrowForward, null, tint = Neutral90)
                ParticipantChip(label = "Learner", name = session.learnerName, picUrl = session.learnerProfilePic, isTeacher = false)
            }

            // Details
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconInfoRow(Icons.Filled.CalendarToday, session.date)
                IconInfoRow(Icons.Filled.Schedule, session.time)
                IconInfoRow(Icons.Filled.Timer, "${session.durationMinutes} min")
            }

            if (session.notes.isNotEmpty()) {
                Text(
                    "📝 ${session.notes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
                )
            }

            // Actions
            // Actions
            if (session.status == SessionStatus.SCHEDULED.name || session.status == SessionStatus.ONGOING.name) {
                if (session.date == "TBD" || session.time == "TBD") {
                    Button(
                        onClick = { showDatePicker = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Filled.Event, null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Schedule Meeting")
                    }
                } else {
                    val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { 
                                if (session.meetLink.isNotEmpty()) {
                                    val safeLink = session.meetLink.replace("meet.jit.si", "meet.ffmuc.net")
                                    try { uriHandler.openUri(safeLink) } catch (e: Exception) { }
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Filled.Videocam, null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Join Meeting", maxLines = 1)
                        }
                        Button(
                            onClick = onMarkComplete,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AcceptedColor)
                        ) {
                            Icon(Icons.Filled.CheckCircle, null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("End Meeting", maxLines = 1)
                        }
                    }
                }
            }

            if (session.status == SessionStatus.COMPLETED.name) {
                Button(
                    onClick = onRateSession,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                ) {
                    Text("⭐ Rate This Session")
                }
            }
        }
    }

    if (showNotesDialog) {
        var notesText by remember { mutableStateOf(session.notes) }
        AlertDialog(
            onDismissRequest = { showNotesDialog = false },
            title = { Text("Session Notes") },
            text = {
                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3, maxLines = 6,
                    shape = RoundedCornerShape(12.dp)
                )
            },
            confirmButton = {
                Button(onClick = { showNotesDialog = false }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { showNotesDialog = false }) { Text("Cancel") }
            }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = java.util.Date(millis)
                            val format = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
                            tempDate = format.format(date)
                            showTimePicker = true // Chain to TimePicker immediately
                        }
                    },
                    enabled = datePickerState.selectedDateMillis != null
                ) { Text("Next") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            title = {
                Text(
                    text = "Time for $tempDate",
                    style = MaterialTheme.typography.titleMedium,
                    color = Blue40
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showTimePicker = false
                    val isPM = timePickerState.hour >= 12
                    val hr = if (timePickerState.hour % 12 == 0) 12 else timePickerState.hour % 12
                    val min = String.format(java.util.Locale.getDefault(), "%02d", timePickerState.minute)
                    val amPm = if (isPM) "PM" else "AM"
                    val tempTime = "$hr:$min $amPm"
                    onSchedule(tempDate, tempTime)
                }) { Text("Confirm Schedule") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}

@Composable
fun ParticipantChip(label: String, name: String, picUrl: String, isTeacher: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = if (isTeacher) Blue40 else Purple40)
        Spacer(Modifier.height(4.dp))
        ProfilePhoto(
            url = picUrl,
            contentDescription = name,
            size = 36.dp
        )
        Text(name.split(" ").first(), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun IconInfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    if (text.isBlank()) return
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(icon, null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurface.copy(0.6f))
        Text(text, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(0.7f))
    }
}
