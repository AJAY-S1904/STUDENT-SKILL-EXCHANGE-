package com.skillswap.ai.ui.sessions;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import com.skillswap.ai.data.model.Session;
import com.skillswap.ai.data.model.SessionStatus;
import com.skillswap.ai.ui.components.*;
import com.skillswap.ai.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a(\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001aN\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u001a&\u0010\u0015\u001a\u00020\u00012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007\u00a8\u0006\u001a"}, d2 = {"IconInfoRow", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "text", "", "ParticipantChip", "label", "name", "picUrl", "isTeacher", "", "SessionCard", "session", "Lcom/skillswap/ai/data/model/Session;", "currentUserId", "onMarkComplete", "Lkotlin/Function0;", "onRateSession", "onSchedule", "Lkotlin/Function2;", "SessionsScreen", "onNavigateToRating", "Lkotlin/Function1;", "viewModel", "Lcom/skillswap/ai/ui/sessions/SessionViewModel;", "app_debug"})
public final class SessionsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SessionsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.skillswap.ai.data.model.Session, kotlin.Unit> onNavigateToRating, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.sessions.SessionViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SessionCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.Session session, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUserId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMarkComplete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRateSession, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSchedule) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ParticipantChip(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String picUrl, boolean isTeacher) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void IconInfoRow(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}