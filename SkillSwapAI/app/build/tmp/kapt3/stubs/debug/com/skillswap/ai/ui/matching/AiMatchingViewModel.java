package com.skillswap.ai.ui.matching;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.AiMatchRequest;
import com.skillswap.ai.data.model.AiMatchResponse;
import com.skillswap.ai.data.model.CandidateProfile;
import com.skillswap.ai.data.repository.AiRepository;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.AuthResult;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0015R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/skillswap/ai/ui/matching/AiMatchingViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "aiRepository", "Lcom/skillswap/ai/data/repository/AiRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/UserRepository;Lcom/skillswap/ai/data/repository/AiRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/matching/AiMatchingUiState;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "findBestMatch", "reset", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AiMatchingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AiRepository aiRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.matching.AiMatchingUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.matching.AiMatchingUiState> uiState = null;
    
    @javax.inject.Inject()
    public AiMatchingViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AiRepository aiRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.matching.AiMatchingUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    public final void findBestMatch() {
    }
    
    public final void clearError() {
    }
    
    public final void reset() {
    }
}