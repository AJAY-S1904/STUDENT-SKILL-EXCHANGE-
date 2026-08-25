package com.skillswap.ai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.MeetingRequest
import com.skillswap.ai.data.model.MeetingStatus
import com.skillswap.ai.data.model.RequestStatus
import com.skillswap.ai.ui.theme.Blue40
import com.skillswap.ai.ui.theme.Purple40

@Composable
fun UnifiedExchangeCard(
    request: ExchangeRequest,
    meeting: MeetingRequest?,
    currentUserId: String,
    onAcceptRequest: () -> Unit,
    onDeclineRequest: () -> Unit,
    onScheduleMeeting: () -> Unit,
    onAcceptMeeting: () -> Unit,
    onChangeTime: () -> Unit,
    onEnterSession: () -> Unit,
    onRateExchange: () -> Unit,
    hasRated: Boolean = false,
    onClick: () -> Unit
) {
    val isSender = request.senderId == currentUserId
    val otherName = if (isSender) request.receiverName else request.senderName
    val otherPic = if (isSender) request.receiverProfilePic else request.senderProfilePic

    val isSessionCompleted = meeting?.meetingStatus == MeetingStatus.COMPLETED.name
    
    // Overall Status
    val overallStatus = when {
        isSessionCompleted -> "Completed"
        meeting?.meetingStatus == MeetingStatus.CONFIRMED.name -> "Confirmed"
        meeting?.meetingStatus == MeetingStatus.PENDING.name -> "Meeting Proposed"
        request.status == RequestStatus.ACCEPTED.name -> "Accepted"
        request.status == RequestStatus.REJECTED.name -> "Declined"
        else -> "Pending"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .shadow(2.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ProfilePhoto(url = otherPic, contentDescription = otherName, size = 48.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(otherName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(
                        if (isSender) "Sent Exchange" else "Incoming Exchange",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                StatusBadge(status = overallStatus)
            }

            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(Modifier.height(16.dp))

            // Skills
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("📚 Teaches", style = MaterialTheme.typography.labelSmall, color = Blue40)
                    Text(request.teachSkill, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("🎓 Learns", style = MaterialTheme.typography.labelSmall, color = Purple40)
                    Text(request.learnSkill, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Progress Indicator
            ExchangeProgress(request = request, meeting = meeting)

            // Meeting Info (only if proposed or confirmed)
            if (meeting != null && (meeting.meetingStatus == MeetingStatus.PENDING.name || meeting.meetingStatus == MeetingStatus.CONFIRMED.name)) {
                Spacer(Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("📅 ${meeting.meetingDate} • 🕒 ${meeting.meetingTime}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                            Spacer(Modifier.height(2.dp))
                            Text("📍 ${meeting.meetingMode}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Smart Actions
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                when {
                    request.status == RequestStatus.PENDING.name -> {
                        if (!isSender) {
                            Button(onClick = onAcceptRequest, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) {
                                Text("Accept", maxLines = 1)
                            }
                            OutlinedButton(onClick = onDeclineRequest, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) {
                                Text("Decline", maxLines = 1)
                            }
                        } else {
                            Text("Waiting for response...", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                        }
                    }
                    request.status == RequestStatus.ACCEPTED.name && (meeting == null || meeting.meetingStatus == MeetingStatus.REJECTED.name) -> {
                        Button(
                            onClick = onScheduleMeeting,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                        ) { Text("Schedule Meeting", fontWeight = FontWeight.Bold) }
                    }
                    meeting?.meetingStatus == MeetingStatus.PENDING.name -> {
                        val isMeetingSender = meeting.learnerId == currentUserId
                        if (!isMeetingSender) {
                            Button(onClick = onAcceptMeeting, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) {
                                Text("Accept Meeting", maxLines = 1)
                            }
                            OutlinedButton(onClick = onChangeTime, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) {
                                Text("Change Time", maxLines = 1)
                            }
                        } else {
                            Text("Waiting for meeting confirmation...", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                        }
                    }
                    isSessionCompleted -> {
                        if (hasRated) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("★★★★★ You rated this exchange", color = Color(0xFFFBC02D), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                            }
                        } else {
                            Button(
                                onClick = onRateExchange,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                            ) { Text("Rate Exchange", fontWeight = FontWeight.Bold, color = Color.White) }
                        }
                    }
                    meeting?.meetingStatus == MeetingStatus.CONFIRMED.name -> {
                        Button(
                            onClick = onEnterSession,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green for ready
                        ) { Text("Enter Session", fontWeight = FontWeight.Bold, color = Color.White) }
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeProgress(request: ExchangeRequest, meeting: MeetingRequest?) {
    val reqAccepted = request.status == RequestStatus.ACCEPTED.name || request.status == RequestStatus.COMPLETED.name
    val meetingConfirmed = meeting?.meetingStatus == MeetingStatus.CONFIRMED.name || meeting?.meetingStatus == MeetingStatus.COMPLETED.name
    val sessionCompleted = meeting?.meetingStatus == MeetingStatus.COMPLETED.name

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProgressStep(label = "Request", isActive = reqAccepted || meetingConfirmed || sessionCompleted, isLast = false)
        ProgressLine(isActive = meetingConfirmed || sessionCompleted)
        ProgressStep(label = "Meeting", isActive = meetingConfirmed || sessionCompleted, isLast = false)
        ProgressLine(isActive = sessionCompleted)
        ProgressStep(
            label = "Session",
            isActive = sessionCompleted, 
            isLast = true
        )
    }
}

@Composable
fun ProgressStep(label: String, isActive: Boolean, isLast: Boolean, highlightText: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = if (isActive) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
            contentDescription = null,
            tint = if (isActive || highlightText) Blue40 else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isActive || highlightText) Blue40 else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            fontWeight = if (isActive || highlightText) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun ProgressLine(isActive: Boolean) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(2.dp)
            .background(if (isActive) Blue40 else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    )
}
