package com.skillswap.ai.ui.sessions;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.Session;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
import com.skillswap.ai.data.repository.SessionRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010!\u001a\u00020\rR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\""}, d2 = {"Lcom/skillswap/ai/ui/sessions/SessionViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "sessionRepository", "Lcom/skillswap/ai/data/repository/SessionRepository;", "creditRepository", "Lcom/skillswap/ai/data/repository/CreditRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/SessionRepository;Lcom/skillswap/ai/data/repository/CreditRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/sessions/SessionUiState;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadSessions", "", "markComplete", "session", "Lcom/skillswap/ai/data/model/Session;", "scheduleSession", "sessionId", "date", "time", "selectTab", "tab", "", "updateNotes", "notes", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SessionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.CreditRepository creditRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.sessions.SessionUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.sessions.SessionUiState> uiState = null;
    
    @javax.inject.Inject()
    public SessionViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.SessionRepository sessionRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.CreditRepository creditRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.sessions.SessionUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    private final void loadSessions() {
    }
    
    public final void markComplete(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.Session session) {
    }
    
    public final void updateNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
    }
    
    public final void scheduleSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String time) {
    }
    
    public final void selectTab(int tab) {
    }
}