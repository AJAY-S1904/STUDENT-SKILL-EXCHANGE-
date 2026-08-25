package com.skillswap.ai.ui.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skillswap.ai.data.model.*
import com.skillswap.ai.ui.components.ProfilePhoto
import com.skillswap.ai.ui.jitsi.JitsiHelper
import com.skillswap.ai.ui.theme.Blue40
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.skillswap.ai.ui.theme.Purple40
import com.skillswap.ai.data.model.RequestStatus
import com.skillswap.ai.data.model.MeetingStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailScreen(
    exchangeId: String,
    onBack: () -> Unit,
    onNavigateToMultiSession: (String) -> Unit,
    onNavigateToSessionCompleted: (String, String) -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    val request = uiState.receivedRequests.find { it.id == exchangeId } 
        ?: uiState.sentRequests.find { it.id == exchangeId }
        
    val meeting = uiState.meetingRequests.find { it.requestId == exchangeId }

    if (request == null) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val isSender = request.senderId == viewModel.currentUserId
    val otherName = if (isSender) request.receiverName else request.senderName
    val otherPic = if (isSender) request.receiverProfilePic else request.senderProfilePic

    DisposableEffect(meeting?.meetingId) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    "org.jitsi.meet.CONFERENCE_JOINED" -> {
                        viewModel.conferenceJoined = true
                    }
                    "org.jitsi.meet.CONFERENCE_TERMINATED" -> {
                        if (viewModel.conferenceJoined && meeting != null) {
                            viewModel.completeMeetingSession(exchangeId, meeting.meetingId)
                            viewModel.conferenceJoined = false
                            onNavigateToSessionCompleted(exchangeId, meeting.meetingId)
                        }
                    }
                }
            }
        }
        val filter = IntentFilter().apply {
            addAction("org.jitsi.meet.CONFERENCE_JOINED")
            addAction("org.jitsi.meet.CONFERENCE_TERMINATED")
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter)

        onDispose {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exchange Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header Profile
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ProfilePhoto(url = otherPic, contentDescription = otherName, size = 64.dp)
                Column {
                    Text(otherName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        if (isSender) "You sent a request" else "Incoming request",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Skills Exchanged
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("📚 Teaches", style = MaterialTheme.typography.labelMedium, color = Blue40)
                        Spacer(Modifier.height(4.dp))
                        Text(request.teachSkill, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🎓 Learns", style = MaterialTheme.typography.labelMedium, color = Purple40)
                        Spacer(Modifier.height(4.dp))
                        Text(request.learnSkill, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Message
            if (request.message.isNotEmpty()) {
                Column {
                    Text("Message", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Text(
                            "\"${request.message}\"",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            // Timeline
            Column {
                Text("Timeline", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                
                val isReqAccepted = request.status == RequestStatus.ACCEPTED.name || request.status == RequestStatus.COMPLETED.name
                val isMeetingScheduled = meeting != null
                val isMeetingConfirmed = meeting?.meetingStatus == MeetingStatus.CONFIRMED.name || meeting?.meetingStatus == MeetingStatus.COMPLETED.name
                val isSessionCompleted = request.status == RequestStatus.COMPLETED.name && meeting?.meetingStatus == MeetingStatus.COMPLETED.name

                val step1State = if (isReqAccepted) TimelineState.COMPLETED else TimelineState.CURRENT
                val step2State = if (isMeetingScheduled) TimelineState.COMPLETED else if (isReqAccepted) TimelineState.CURRENT else TimelineState.PENDING
                val step3State = if (isMeetingConfirmed) TimelineState.COMPLETED else if (isMeetingScheduled) TimelineState.CURRENT else TimelineState.PENDING
                val step4State = if (isSessionCompleted) TimelineState.COMPLETED else if (isMeetingConfirmed) TimelineState.CURRENT else TimelineState.PENDING
                val step5State = if (isSessionCompleted) TimelineState.COMPLETED else TimelineState.PENDING
                
                TimelineStep("Request Sent", step1State)
                TimelineStep("Request Accepted", step2State)
                TimelineStep("Meeting Scheduled", step3State)
                TimelineStep("Meeting Confirmed", step4State)
                TimelineStep("Session Completed", step5State, isLast = true)
            }

            // Meeting Info
            if (meeting != null && (meeting.meetingStatus == MeetingStatus.PENDING.name || meeting.meetingStatus == MeetingStatus.CONFIRMED.name)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Meeting Details", style = MaterialTheme.typography.labelMedium, color = Blue40)
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Date:", style = MaterialTheme.typography.bodyMedium)
                            Text(meeting.meetingDate, fontWeight = FontWeight.Bold)
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Time:", style = MaterialTheme.typography.bodyMedium)
                            Text(meeting.meetingTime, fontWeight = FontWeight.Bold)
                        }
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Location:", style = MaterialTheme.typography.bodyMedium)
                            Text(meeting.meetingMode, fontWeight = FontWeight.Bold, color = Blue40)
                        }
                        if (meeting.meetingLocationOrLink.isNotEmpty()) {
                            Text(meeting.meetingLocationOrLink, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // Footer Action
            if (meeting != null) {
                if (meeting.meetingStatus == MeetingStatus.CONFIRMED.name && request.status != RequestStatus.COMPLETED.name) {
                    Button(
                        onClick = { 
                            JitsiHelper.launchMeeting(
                                context = context,
                                meeting = meeting,
                                request = request,
                                currentUserId = viewModel.currentUserId
                            )
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Enter Session", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                } else if (meeting.meetingStatus == MeetingStatus.COMPLETED.name && request.status == RequestStatus.COMPLETED.name) {
                    val hasRated = uiState.ratedMeetingIds.contains(meeting.meetingId)
                    
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50))
                            Spacer(Modifier.width(8.dp))
                            Text("Session Completed", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                        }
                        
                        Spacer(Modifier.height(16.dp))
                        
                        if (hasRated) {
                            Text("★★★★★ You rated this exchange", color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)
                        } else {
                            Button(
                                onClick = { onNavigateToSessionCompleted(exchangeId, meeting.meetingId) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Rate Exchange", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class TimelineState {
    COMPLETED, CURRENT, PENDING
}

@Composable
fun TimelineStep(label: String, state: TimelineState, isLast: Boolean = false) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(24.dp)) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = when (state) {
                            TimelineState.COMPLETED -> Color(0xFF4CAF50)
                            TimelineState.CURRENT -> Blue40
                            TimelineState.PENDING -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                        },
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (state == TimelineState.COMPLETED) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(10.dp)
                    )
                } else if (state == TimelineState.CURRENT) {
                    Box(modifier = Modifier.size(6.dp).background(Color.White, RoundedCornerShape(50)))
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(
                            when (state) {
                                TimelineState.COMPLETED -> Color(0xFF4CAF50)
                                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                            }
                        )
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (state != TimelineState.PENDING) FontWeight.Bold else FontWeight.Normal,
            color = if (state != TimelineState.PENDING) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
