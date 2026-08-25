package com.skillswap.ai.ui.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.jitsi.JitsiHelper
import com.skillswap.ai.ui.theme.Blue40
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.MeetingRequest

data class UnifiedExchange(
    val request: ExchangeRequest,
    val meeting: MeetingRequest?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToSessions: () -> Unit,
    onNavigateToMultiSession: (String) -> Unit,
    onNavigateToRating: (String, String, String, String, String) -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Unified requests based on selected tab
    val unifiedExchanges = remember(uiState) {
        val requests = if (uiState.selectedTab == 0) uiState.receivedRequests else uiState.sentRequests
        requests.map { req ->
            val meeting = uiState.meetingRequests.find { it.requestId == req.id }
            UnifiedExchange(req, meeting)
        }.sortedByDescending { it.request.updatedAt }
    }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    
    var showScheduleDialog by remember { mutableStateOf(false) }
    var selectedRequestToSchedule by remember { mutableStateOf<ExchangeRequest?>(null) }
    
    var meetingMode by remember { mutableStateOf("Online") }
    var meetingLocationOrLink by remember { mutableStateOf("") }
    var meetingNotes by remember { mutableStateOf("") }
    var tempDate by remember { mutableStateOf("") }
    var tempTime by remember { mutableStateOf("") }
    val context = androidx.compose.ui.platform.LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshRatings()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

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
                    "Manage your skill exchanges",
                    style = MaterialTheme.typography.bodyMedium,
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
                        val pendingCount = uiState.receivedRequests.count { it.status == com.skillswap.ai.data.model.RequestStatus.PENDING.name }
                        if (pendingCount > 0) {
                            Badge { Text("$pendingCount") }
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

        if (unifiedExchanges.isEmpty()) {
            EmptyState(
                emoji = if (uiState.selectedTab == 0) "📬" else "📤",
                title = if (uiState.selectedTab == 0) "You don't have any incoming requests." else "You haven't sent any exchange requests.",
                subtitle = "Connect with students to start exchanging skills.",
                actionText = "Find Students",
                onAction = onNavigateToSearch
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(unifiedExchanges, key = { it.request.id }) { exchange ->
                    UnifiedExchangeCard(
                        request = exchange.request,
                        meeting = exchange.meeting,
                        currentUserId = viewModel.currentUserId,
                        onAcceptRequest = { viewModel.acceptRequest(exchange.request) },
                        onDeclineRequest = { viewModel.rejectRequest(exchange.request.id) },
                        onScheduleMeeting = {
                            selectedRequestToSchedule = exchange.request
                            showScheduleDialog = true
                        },
                        onAcceptMeeting = {
                            if (exchange.meeting != null) {
                                viewModel.acceptMeeting(exchange.meeting, exchange.request)
                            }
                        },
                        onChangeTime = {
                            // Can reuse schedule dialog to propose new time
                            selectedRequestToSchedule = exchange.request
                            showScheduleDialog = true
                        },
                        onEnterSession = {
                            if (exchange.meeting != null) {
                                JitsiHelper.launchMeeting(
                                    context = context,
                                    meeting = exchange.meeting,
                                    request = exchange.request,
                                    currentUserId = viewModel.currentUserId
                                )
                            }
                        },
                        onRateExchange = {
                            if (exchange.meeting != null) {
                                val uid = viewModel.currentUserId
                                val isSender = exchange.request.senderId == uid
                                val ratedUserId = if (isSender) exchange.request.receiverId else exchange.request.senderId
                                val ratedUserName = if (isSender) exchange.request.receiverName else exchange.request.senderName
                                val skill = if (isSender) exchange.request.teachSkill else exchange.request.learnSkill
                                onNavigateToRating(exchange.meeting.meetingId, exchange.request.id, ratedUserId, ratedUserName, skill)
                            }
                        },
                        hasRated = exchange.meeting != null && uiState.ratedMeetingIds.contains(exchange.meeting.meetingId),
                        onClick = { onNavigateToDetail(exchange.request.id) }
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
