package com.skillswap.ai.ui.notifications;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.skillswap.ai.data.model.AppNotification;
import com.skillswap.ai.data.model.NotificationType;
import com.skillswap.ai.ui.theme.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a2\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006\u000b"}, d2 = {"NotificationItem", "", "notification", "Lcom/skillswap/ai/data/model/AppNotification;", "onClick", "Lkotlin/Function0;", "NotificationsScreen", "viewModel", "Lcom/skillswap/ai/ui/notifications/NotificationViewModel;", "onNavigateToRequests", "onNavigateToSessions", "app_debug"})
public final class NotificationsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void NotificationsScreen(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.notifications.NotificationViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToRequests, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSessions) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NotificationItem(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.AppNotification notification, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}