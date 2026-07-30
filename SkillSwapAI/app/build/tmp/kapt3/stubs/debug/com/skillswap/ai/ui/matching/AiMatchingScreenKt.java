package com.skillswap.ai.ui.matching;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.skillswap.ai.data.model.AiMatchResponse;
import com.skillswap.ai.ui.components.*;
import com.skillswap.ai.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a,\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0004H\u0007\u001a<\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"AiMatchingScreen", "", "onSendRequest", "Lkotlin/Function1;", "", "viewModel", "Lcom/skillswap/ai/ui/matching/AiMatchingViewModel;", "requestViewModel", "Lcom/skillswap/ai/ui/requests/RequestViewModel;", "AiRecommendationCard", "response", "Lcom/skillswap/ai/data/model/AiMatchResponse;", "Lkotlin/Function0;", "onReset", "InfoChip", "text", "ScoreCard", "modifier", "Landroidx/compose/ui/Modifier;", "label", "value", "color", "Landroidx/compose/ui/graphics/Color;", "emoji", "ScoreCard-42QJj7c", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "app_debug"})
public final class AiMatchingScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AiMatchingScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSendRequest, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.matching.AiMatchingViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.requests.RequestViewModel requestViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AiRecommendationCard(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.AiMatchResponse response, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSendRequest, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReset) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void InfoChip(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}