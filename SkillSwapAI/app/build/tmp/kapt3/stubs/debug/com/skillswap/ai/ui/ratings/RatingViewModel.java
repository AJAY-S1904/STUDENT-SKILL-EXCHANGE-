package com.skillswap.ai.ui.ratings;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.Rating;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.AuthResult;
import com.skillswap.ai.data.repository.RatingRepository;
import com.skillswap.ai.data.repository.SessionRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ&\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/skillswap/ai/ui/ratings/RatingViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "ratingRepository", "Lcom/skillswap/ai/data/repository/RatingRepository;", "sessionRepository", "Lcom/skillswap/ai/data/repository/SessionRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/RatingRepository;Lcom/skillswap/ai/data/repository/SessionRepository;Lcom/skillswap/ai/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/ratings/RatingUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadSessionDetails", "", "sessionId", "", "setFeedback", "feedback", "setStars", "stars", "", "submitRating", "ratedUserId", "ratedUserName", "skill", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RatingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.RatingRepository ratingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.ratings.RatingUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.ratings.RatingUiState> uiState = null;
    
    @javax.inject.Inject()
    public RatingViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.RatingRepository ratingRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.SessionRepository sessionRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.ratings.RatingUiState> getUiState() {
        return null;
    }
    
    public final void loadSessionDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
    }
    
    public final void setStars(float stars) {
    }
    
    public final void setFeedback(@org.jetbrains.annotations.NotNull()
    java.lang.String feedback) {
    }
    
    public final void submitRating(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String ratedUserId, @org.jetbrains.annotations.NotNull()
    java.lang.String ratedUserName, @org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
}