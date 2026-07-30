package com.skillswap.ai.ui.dashboard;

import androidx.compose.animation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.foundation.layout.ExperimentalLayoutApi;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.ui.components.*;
import com.skillswap.ai.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001al\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a\u001e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00a8\u0006\u0011"}, d2 = {"DashboardScreen", "", "onNavigateToSearch", "Lkotlin/Function0;", "onNavigateToProfile", "onNavigateToMatching", "onNavigateToStudentProfile", "Lkotlin/Function1;", "", "onNavigateToNotifications", "onNavigateToSessions", "viewModel", "Lcom/skillswap/ai/ui/dashboard/DashboardViewModel;", "RecommendedStudentCard", "user", "Lcom/skillswap/ai/data/model/User;", "onClick", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSearch, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToProfile, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToMatching, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToStudentProfile, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToNotifications, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSessions, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RecommendedStudentCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}