package com.skillswap.ai.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.RequestStatus
import com.skillswap.ai.data.model.User
import com.skillswap.ai.ui.theme.*

// ── Gradient Background ───────────────────────────────────────────────────────
@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) { content() }
}

// ── Gradient Banner ───────────────────────────────────────────────────────────
@Composable
fun GradientBanner(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(GradientStart, GradientMid, GradientEnd),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 600f)
                )
            )
            .padding(24.dp),
        content = content
    )
}

// ── Skill Chip ────────────────────────────────────────────────────────────────
@Composable
fun SkillChip(
    skill: String,
    isTeach: Boolean = true,
    isVerified: Boolean = false,
    onDelete: (() -> Unit)? = null
) {
    val bgColor = if (isTeach) Blue90 else Purple90
    val contentColor = if (isTeach) Blue30 else Purple30

    Surface(
        shape = RoundedCornerShape(50),
        color = bgColor,
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = skill,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                fontWeight = FontWeight.SemiBold
            )
            if (isVerified) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Verified",
                    tint = androidx.compose.ui.graphics.Color(0xFF4CAF50),
                    modifier = Modifier.size(16.dp)
                )
            }
            if (onDelete != null) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(contentColor)
                        .clickable { onDelete() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("×", color = bgColor, fontSize = 12.sp)
                }
            }
        }
    }
}

// ── Student Card ──────────────────────────────────────────────────────────────
@Composable
fun StudentCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Avatar
            Box {
                ProfilePhoto(
                    url = user.profilePictureUrl,
                    contentDescription = user.name,
                    size = 56.dp
                )
                // Online/Offline Indicator
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-2).dp, y = (-2).dp)
                        .clip(CircleShape)
                        .background(if (user.isOnline) Color.Green else Color.Gray)
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(if (user.isOnline) Color.Green else Color.Gray)
                ) {
                    // White border
                    Box(modifier = Modifier.fillMaxSize().background(Color.White).padding(2.dp).clip(CircleShape).background(if (user.isOnline) Color.Green else Color.Gray))
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${user.studentId} • ${user.department} • ${user.college}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    val verifiedTeachSkills = user.teachSkills.filter { skill -> user.verifiedSkills.any { it.equals(skill, ignoreCase = true) } }
                    verifiedTeachSkills.take(2).forEach { skill ->
                        SkillChip(skill = skill, isTeach = true, isVerified = true) 
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFB300),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = String.format("%.1f", user.rating),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${user.skillCredits}cr",
                    style = MaterialTheme.typography.labelSmall,
                    color = Purple40
                )
            }
        }
    }
}

// ── Star Rating Bar ───────────────────────────────────────────────────────────
@Composable
fun StarRatingBar(
    rating: Float,
    onRatingChanged: ((Float) -> Unit)? = null,
    maxStars: Int = 5,
    starSize: Int = 32
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(maxStars) { index ->
            val filled = index < rating
            Icon(
                imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = if (filled) Color(0xFFFFB300) else Color(0xFFBDBDBD),
                modifier = Modifier
                    .size(starSize.dp)
                    .then(
                        if (onRatingChanged != null)
                            Modifier.clickable { onRatingChanged(index + 1f) }
                        else Modifier
                    )
            )
        }
    }
}

// ── Request Status Badge ──────────────────────────────────────────────────────
@Composable
fun StatusBadge(status: String) {
    val (bg, text) = when (status) {
        RequestStatus.PENDING.name   -> PendingColor.copy(alpha = 0.15f) to PendingColor
        RequestStatus.ACCEPTED.name  -> AcceptedColor.copy(alpha = 0.15f) to AcceptedColor
        RequestStatus.COMPLETED.name -> CompletedColor.copy(alpha = 0.15f) to CompletedColor
        RequestStatus.CANCELLED.name -> CancelledColor.copy(alpha = 0.15f) to CancelledColor
        RequestStatus.REJECTED.name  -> RejectedColor.copy(alpha = 0.15f) to RejectedColor
        else -> MaterialTheme.colorScheme.surface to MaterialTheme.colorScheme.onSurface
    }
    Surface(shape = RoundedCornerShape(50), color = bg) {
        Text(
            text = status,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = text,
            fontWeight = FontWeight.Bold
        )
    }
}

// ── Request Card ──────────────────────────────────────────────────────────────
@Composable
fun RequestCard(
    request: ExchangeRequest,
    currentUserId: String,
    otherUserPhoto: String? = null,
    onAccept: (() -> Unit)? = null,
    onReject: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null,
    onViewMeeting: (() -> Unit)? = null,
    onReschedule: (() -> Unit)? = null
) {
    val isSender = request.senderId == currentUserId
    val otherName = if (isSender) request.receiverName else request.senderName
    val otherPic = otherUserPhoto ?: if (isSender) request.receiverProfilePic else request.senderProfilePic

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfilePhoto(
                    url = otherPic,
                    contentDescription = otherName,
                    size = 48.dp
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        otherName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        if (isSender) "You sent a request" else "Sent you a request",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                StatusBadge(request.status)
            }

            Spacer(Modifier.height(12.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("📚 Teach", style = MaterialTheme.typography.labelSmall, color = Blue40)
                    Text(request.teachSkill, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("🎓 Learn", style = MaterialTheme.typography.labelSmall, color = Purple40)
                    Text(request.learnSkill, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                }
            }


            if (request.message.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "\"${request.message}\"",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            if (request.status == RequestStatus.PENDING.name) {
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!isSender && onAccept != null) {
                        Button(
                            onClick = onAccept,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("Accept", maxLines = 1) }
                    }
                }
            } else if (request.status == RequestStatus.ACCEPTED.name) {
                Spacer(Modifier.height(12.dp))
                if (isSender && onViewMeeting != null) {
                    Button(
                        onClick = onViewMeeting,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                    ) { Text("Schedule Meeting", fontWeight = FontWeight.Bold) }
                } else {
                    Text(
                        "Waiting for learner to schedule a meeting...",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

// ── Meeting Request Card ──────────────────────────────────────────────────────
@Composable
fun MeetingRequestCard(
    meeting: com.skillswap.ai.data.model.MeetingRequest,
    currentUserId: String,
    onAccept: (() -> Unit)? = null,
    onReject: (() -> Unit)? = null,
    onGoToSession: (() -> Unit)? = null,
    onNavigateToMultiSession: ((String) -> Unit)? = null
) {
    val isSender = meeting.learnerId == currentUserId
    val otherName = if (isSender) meeting.teacherName else meeting.learnerName
    val otherPic = if (isSender) meeting.teacherProfilePic else meeting.learnerProfilePic

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfilePhoto(url = otherPic, contentDescription = otherName, size = 48.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(otherName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                    Text(
                        if (isSender) "You proposed a meeting" else "Proposed a meeting",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                StatusBadge(meeting.meetingStatus)
            }

            Spacer(Modifier.height(12.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("📅 ${meeting.meetingDate}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
                Text("🕒 ${meeting.meetingTime}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("📍 ${meeting.meetingMode}", style = MaterialTheme.typography.bodySmall, color = Blue40, fontWeight = FontWeight.SemiBold)
                if (meeting.meetingLocationOrLink.isNotBlank()) {
                    Text(meeting.meetingLocationOrLink, style = MaterialTheme.typography.bodySmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }

            if (meeting.notes.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text("\"${meeting.notes}\"", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            }

            if (meeting.meetingStatus == com.skillswap.ai.data.model.MeetingStatus.PENDING.name) {
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!isSender && onAccept != null) {
                        Button(
                            onClick = onAccept,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("Accept", maxLines = 1) }
                    }
                    if (!isSender && onReject != null) {
                        OutlinedButton(
                            onClick = onReject,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("Reject", maxLines = 1) }
                    }
                    if (isSender) {
                        Text(
                            "Waiting for teacher to approve.",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            } else if (meeting.meetingStatus == com.skillswap.ai.data.model.MeetingStatus.CONFIRMED.name) {
                if (onGoToSession != null || onNavigateToMultiSession != null) {
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (onGoToSession != null) {
                            Button(
                                onClick = onGoToSession,
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                            ) { Text("Go to Session", fontWeight = FontWeight.Bold, maxLines = 1) }
                        }
                        if (onNavigateToMultiSession != null) {
                            OutlinedButton(
                                onClick = { onNavigateToMultiSession(meeting.meetingId) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            ) { Text("Manage Sessions", maxLines = 1) }
                        }
                    }
                }
            }
        }
    }
}

// ── Loading Shimmer ───────────────────────────────────────────────────────────
@Composable
fun ShimmerCard(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "shimmer"
    )
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE0E0E0),
            Color(0xFFF5F5F5),
            Color(0xFFE0E0E0)
        ),
        start = Offset(translateAnim - 200f, 0f),
        end = Offset(translateAnim, 0f)
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .shadow(2.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(Modifier.fillMaxSize().background(brush))
    }
}

// ── Section Header ────────────────────────────────────────────────────────────
@Composable
fun SectionHeader(
    title: String,
    actionText: String = "",
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        if (actionText.isNotEmpty() && onAction != null) {
            TextButton(onClick = onAction) {
                Text(actionText, style = MaterialTheme.typography.labelMedium, color = Blue40)
            }
        }
    }
}

// ── Credit Balance Chip ───────────────────────────────────────────────────────
@Composable
fun CreditBalanceChip(credits: Int) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Purple90
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text("⭐", fontSize = 16.sp)
            Text(
                "$credits Credits",
                style = MaterialTheme.typography.labelLarge,
                color = Purple30,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ── Empty State ───────────────────────────────────────────────────────────────
@Composable
fun EmptyState(
    emoji: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(emoji, fontSize = 56.sp)
        Spacer(Modifier.height(16.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

// ── Animated Profile Photo ────────────────────────────────────────────────────
@Composable
fun ProfilePhoto(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 56.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        val imageBitmap = remember(url) {
            if (url.startsWith("data:image")) {
                try {
                    val base64String = url.substringAfter("base64,")
                    val bytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT)
                    val bitmap = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    bitmap?.asImageBitmap()
                } catch (e: Exception) {
                    null
                }
            } else null
        }

        // Static inner image
        if (imageBitmap != null) {
            androidx.compose.foundation.Image(
                bitmap = imageBitmap,
                contentDescription = contentDescription,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentScale = ContentScale.Crop
            )
        } else {
            if (url.isEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(size / 6),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            } else {
                AsyncImage(
                    model = url,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
