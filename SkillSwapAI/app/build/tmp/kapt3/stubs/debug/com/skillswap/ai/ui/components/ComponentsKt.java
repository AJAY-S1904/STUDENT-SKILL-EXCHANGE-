package com.skillswap.ai.ui.components;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import com.skillswap.ai.data.model.ExchangeRequest;
import com.skillswap.ai.data.model.RequestStatus;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a*\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u001b\u0010\u000b\u001a\u00020\u00012\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00010\r\u00a2\u0006\u0002\b\u000eH\u0007\u001a0\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\u00a2\u0006\u0002\b\u000e\u00a2\u0006\u0002\b\u0012H\u0007\u001aN\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rH\u0007\u001a8\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a~\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u00062\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\u0010\b\u0002\u0010\'\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rH\u0007\u001a,\u0010(\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010)\u001a\u00020\u00062\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rH\u0007\u001a\u0012\u0010+\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a,\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020/2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rH\u0007\u001a<\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u0002032\u0016\b\u0002\u00104\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00102\b\b\u0002\u00105\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u00020\u0003H\u0007\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u0006H\u0007\u001a(\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006="}, d2 = {"CreditBalanceChip", "", "credits", "", "EmptyState", "emoji", "", "title", "subtitle", "modifier", "Landroidx/compose/ui/Modifier;", "GradientBackground", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "GradientBanner", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lkotlin/ExtensionFunctionType;", "MeetingRequestCard", "meeting", "Lcom/skillswap/ai/data/model/MeetingRequest;", "currentUserId", "onAccept", "onReject", "onGoToSession", "ProfilePhoto", "url", "contentDescription", "size", "Landroidx/compose/ui/unit/Dp;", "ProfilePhoto-eqLRuRQ", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;F)V", "RequestCard", "request", "Lcom/skillswap/ai/data/model/ExchangeRequest;", "otherUserPhoto", "onCancel", "onViewMeeting", "onReschedule", "SectionHeader", "actionText", "onAction", "ShimmerCard", "SkillChip", "skill", "isTeach", "", "onDelete", "StarRatingBar", "rating", "", "onRatingChanged", "maxStars", "starSize", "StatusBadge", "status", "StudentCard", "user", "Lcom/skillswap/ai/data/model/User;", "onClick", "app_debug"})
public final class ComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void GradientBackground(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GradientBanner(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.BoxScope, kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SkillChip(@org.jetbrains.annotations.NotNull()
    java.lang.String skill, boolean isTeach, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StudentCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StarRatingBar(float rating, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onRatingChanged, int maxStars, int starSize) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusBadge(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RequestCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUserId, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserPhoto, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAccept, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReject, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancel, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onViewMeeting, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReschedule) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MeetingRequestCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.MeetingRequest meeting, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUserId, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAccept, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReject, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onGoToSession) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShimmerCard(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String actionText, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAction) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CreditBalanceChip(int credits) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState(@org.jetbrains.annotations.NotNull()
    java.lang.String emoji, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}