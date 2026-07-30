package com.skillswap.ai.ui.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.Blue40
import com.skillswap.ai.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsScreen(
    onNavigateToSessions: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val requests = if (uiState.selectedTab == 0) uiState.receivedRequests else uiState.sentRequests
    val meetings = if (uiState.selectedTab == 0) 
        uiState.meetingRequests.filter { it.teacherId == viewModel.currentUserId }
    else 
        uiState.meetingRequests.filter { it.learnerId == viewModel.currentUserId }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    
    var showScheduleDialog by remember { mutableStateOf(false) }
    var selectedRequestToSchedule by remember { mutableStateOf<com.skillswap.ai.data.model.ExchangeRequest?>(null) }
    
    var meetingMode by remember { mutableStateOf("Online") }
    var meetingLocationOrLink by remember { mutableStateOf("") }
    var meetingNotes by remember { mutableStateOf("") }
    var tempDate by remember { mutableStateOf("") }
    var tempTime by remember { mutableStateOf("") }
    val context = androidx.compose.ui.platform.LocalContext.current

    LaunchedEffect(uiState.error) {
        if (uiState.error != null) {
            android.widget.Toast.makeText(context, uiState.error, android.widget.Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(uiState.successMessage) {
        if (uiState.successMessage != null) {
            android.widget.Toast.makeText(context, uiState.successMessage, android.widget.Toast.LENGTH_LONG).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        GradientBanner {
            Column {
                Text(
                    "Exchange Requests",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "${uiState.receivedRequests.size} received • ${uiState.sentRequests.size} sent",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.8f)
                )
            }
        }

        // Tab Row
        TabRow(
            selectedTabIndex = uiState.selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = Blue40
        ) {
            Tab(
                selected = uiState.selectedTab == 0,
                onClick = { viewModel.selectTab(0) },
                text = {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Received")
                        if (uiState.receivedRequests.count { it.status == com.skillswap.ai.data.model.RequestStatus.PENDING.name } > 0) {
                            Badge {
                                Text("${uiState.receivedRequests.count { it.status == com.skillswap.ai.data.model.RequestStatus.PENDING.name }}")
                            }
                        }
                    }
                }
            )
            Tab(
                selected = uiState.selectedTab == 1,
                onClick = { viewModel.selectTab(1) },
                text = { Text("Sent") }
            )
        }

        if (requests.isEmpty() && meetings.isEmpty()) {
            EmptyState(
                emoji = if (uiState.selectedTab == 0) "📬" else "📤",
                title = if (uiState.selectedTab == 0) "No requests received" else "No requests sent",
                subtitle = if (uiState.selectedTab == 0)
                    "When students send you requests or meetings, they'll appear here"
                else
                    "Find students and send skill exchange requests"
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (requests.isNotEmpty()) {
                    item { Text("Skill Requests", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Blue40) }
                }
                items(requests, key = { "req_${it.id}" }) { request ->
                    RequestCard(
                        request = request,
                        currentUserId = viewModel.currentUserId,
                        onAccept = { viewModel.acceptRequest(request) },
                        onReject = { viewModel.rejectRequest(request.id) },
                        onCancel = { viewModel.cancelRequest(request.id) },
                        onViewMeeting = { 
                            selectedRequestToSchedule = request
                            showScheduleDialog = true
                        }
                    )
                }

                if (meetings.isNotEmpty()) {
                    item { Spacer(Modifier.height(8.dp)) }
                    item { Text("Meeting Requests", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Purple40) }
                }
                items(meetings, key = { "meet_${it.meetingId}" }) { meeting ->
                    MeetingRequestCard(
                        meeting = meeting,
                        currentUserId = viewModel.currentUserId,
                        onAccept = {
                            val req = uiState.receivedRequests.find { it.id == meeting.requestId } 
                                ?: uiState.sentRequests.find { it.id == meeting.requestId }
                            if (req != null) {
                                viewModel.acceptMeeting(meeting, req)
                            }
                        },
                        onReject = { viewModel.rejectMeeting(meeting.meetingId) },
                        onGoToSession = onNavigateToSessions
                    )
                }
                
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }

    if (showScheduleDialog && selectedRequestToSchedule != null) {
        val req = selectedRequestToSchedule!!
        AlertDialog(
            onDismissRequest = { showScheduleDialog = false },
            title = { Text("Schedule Meeting", color = Blue40) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = tempDate,
                            onValueChange = {},
                            label = { Text("Date") },
                            modifier = Modifier.weight(1f),
                            readOnly = true,
                            trailingIcon = { IconButton(onClick = { showDatePicker = true }) { Text("📅") } }
                        )
                        OutlinedTextField(
                            value = tempTime,
                            onValueChange = {},
                            label = { Text("Time") },
                            modifier = Modifier.weight(1f),
                            readOnly = true,
                            trailingIcon = { IconButton(onClick = { showTimePicker = true }) { Text("🕒") } }
                        )
                    }
                    OutlinedTextField(
                        value = meetingMode,
                        onValueChange = { meetingMode = it },
                        label = { Text("Mode (Online/Offline)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = meetingLocationOrLink,
                        onValueChange = { meetingLocationOrLink = it },
                        label = { Text("Location or Link") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = meetingNotes,
                        onValueChange = { meetingNotes = it },
                        label = { Text("Notes (Optional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.scheduleMeeting(
                        req, tempDate, tempTime, meetingMode, meetingLocationOrLink, meetingNotes
                    )
                    showScheduleDialog = false
                    selectedRequestToSchedule = null
                }, enabled = tempDate.isNotEmpty() && tempTime.isNotEmpty()) {
                    Text("Send Meeting Request")
                }
            },
            dismissButton = {
                TextButton(onClick = { showScheduleDialog = false }) { Text("Cancel") }
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
                            showTimePicker = true
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
            title = { Text("Time for $tempDate", color = Blue40) },
            text = { TimePicker(state = timePickerState) },
            confirmButton = {
                TextButton(onClick = {
                    showTimePicker = false
                    val isPM = timePickerState.hour >= 12
                    val hr = if (timePickerState.hour % 12 == 0) 12 else timePickerState.hour % 12
                    val min = String.format(java.util.Locale.getDefault(), "%02d", timePickerState.minute)
                    val amPm = if (isPM) "PM" else "AM"
                    tempTime = "$hr:$min $amPm"
                }) { Text("Confirm") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            }
        )
    }
}
