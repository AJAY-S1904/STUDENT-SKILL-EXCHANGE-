package com.skillswap.ai.ui.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.data.model.AppNotification
import com.skillswap.ai.data.model.NotificationType
import com.skillswap.ai.ui.components.EmptyState
import com.skillswap.ai.ui.components.GradientBanner
import com.skillswap.ai.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
    onNavigateToRequests: () -> Unit = {},
    onNavigateToSessions: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        GradientBanner {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Notifications", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    if (uiState.unreadCount > 0) {
                        Text("${uiState.unreadCount} unread", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.8f))
                    }
                }
                if (uiState.unreadCount > 0) {
                    TextButton(onClick = { viewModel.markAllAsRead() }) {
                        Text("Mark all read", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }

        if (uiState.notifications.isEmpty()) {
            EmptyState(
                emoji = "🔔",
                title = "No notifications",
                subtitle = "You're all caught up! New notifications will appear here.",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(uiState.notifications, key = { it.id }) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = { 
                            viewModel.markAsRead(notification.id)
                            when (notification.type) {
                                NotificationType.NEW_REQUEST.name,
                                NotificationType.REQUEST_ACCEPTED.name,
                                NotificationType.REQUEST_REJECTED.name -> onNavigateToRequests()
                                
                                NotificationType.SESSION_REMINDER.name -> onNavigateToSessions()
                                // Add more navigations if needed later
                            }
                        }
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }
}

@Composable
fun NotificationItem(notification: AppNotification, onClick: () -> Unit) {
    val bgColor = if (!notification.getIsActuallyRead())
        MaterialTheme.colorScheme.primaryContainer.copy(0.3f)
    else MaterialTheme.colorScheme.surface

    val (emoji, iconColor) = when (notification.type) {
        NotificationType.NEW_REQUEST.name      -> "📩" to Blue40
        NotificationType.REQUEST_ACCEPTED.name -> "✅" to AcceptedColor
        NotificationType.REQUEST_REJECTED.name -> "❌" to CancelledColor
        NotificationType.SESSION_REMINDER.name -> "⏰" to PendingColor
        NotificationType.RATING_REMINDER.name  -> "⭐" to Color(0xFFFFB300)
        NotificationType.CREDIT_EARNED.name    -> "💎" to AcceptedColor
        NotificationType.CREDIT_SPENT.name     -> "💸" to PendingColor
        else                                   -> "🔔" to Blue40
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 22.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        notification.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = if (!notification.getIsActuallyRead()) FontWeight.Bold else FontWeight.Normal
                    )
                    if (notification.createdAt > 0) {
                        Text(
                            SimpleDateFormat("MMM dd", Locale.getDefault()).format(java.util.Date(notification.createdAt)),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                        )
                    }
                }
                Text(
                    notification.body,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
                )
            }

            if (!notification.getIsActuallyRead()) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Blue40)
                )
            }
        }
    }

    Divider(color = MaterialTheme.colorScheme.outline.copy(0.1f))
}
