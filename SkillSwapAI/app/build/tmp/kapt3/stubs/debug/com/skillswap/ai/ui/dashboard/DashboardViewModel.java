package com.skillswap.ai.ui.dashboard;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.ExchangeRequest;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import com.skillswap.ai.data.repository.RequestRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0006\u0010\u001c\u001a\u00020\u001bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/skillswap/ai/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "requestRepository", "Lcom/skillswap/ai/data/repository/RequestRepository;", "notificationRepository", "Lcom/skillswap/ai/data/repository/NotificationRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/UserRepository;Lcom/skillswap/ai/data/repository/RequestRepository;Lcom/skillswap/ai/data/repository/NotificationRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/dashboard/DashboardUiState;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "getAiRecommendedUsers", "", "Lcom/skillswap/ai/data/model/User;", "getPopularSkills", "loadDashboard", "", "refresh", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.RequestRepository requestRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.NotificationRepository notificationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.dashboard.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.dashboard.DashboardUiState> uiState = null;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.RequestRepository requestRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.NotificationRepository notificationRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.dashboard.DashboardUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    private final void loadDashboard() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> getAiRecommendedUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPopularSkills() {
        return null;
    }
    
    public final void refresh() {
    }
}