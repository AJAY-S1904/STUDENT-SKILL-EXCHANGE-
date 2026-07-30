package com.skillswap.ai.ui.profile;

import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.layout.ExperimentalLayoutApi;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import com.skillswap.ai.ui.components.*;
import com.skillswap.ai.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007\u001aF\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007\u001a.\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a \u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0018"}, d2 = {"InfoRow", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "value", "ProfileInfoCard", "email", "college", "department", "year", "bio", "availability", "", "experience", "ProfileScreen", "onLogout", "Lkotlin/Function0;", "onNavigateToSkills", "viewModel", "Lcom/skillswap/ai/ui/profile/ProfileViewModel;", "ProfileStat", "emoji", "app_debug"})
public final class ProfileScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSkills, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.profile.ProfileViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileStat(@org.jetbrains.annotations.NotNull()
    java.lang.String emoji, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    java.lang.String label) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileInfoCard(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String college, @org.jetbrains.annotations.NotNull()
    java.lang.String department, @org.jetbrains.annotations.NotNull()
    java.lang.String year, @org.jetbrains.annotations.NotNull()
    java.lang.String bio, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, @org.jetbrains.annotations.NotNull()
    java.lang.String experience) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void InfoRow(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
}