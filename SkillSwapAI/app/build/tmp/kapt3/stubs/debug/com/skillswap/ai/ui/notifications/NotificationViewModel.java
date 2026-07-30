package com.skillswap.ai.ui.notifications;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.AppNotification;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/skillswap/ai/ui/notifications/NotificationViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "notificationRepository", "Lcom/skillswap/ai/data/repository/NotificationRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/NotificationRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/notifications/NotificationUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "markAllAsRead", "", "markAsRead", "notificationId", "", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class NotificationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.NotificationRepository notificationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.notifications.NotificationUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.notifications.NotificationUiState> uiState = null;
    
    @javax.inject.Inject()
    public NotificationViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.NotificationRepository notificationRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.notifications.NotificationUiState> getUiState() {
        return null;
    }
    
    public final void markAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String notificationId) {
    }
    
    public final void markAllAsRead() {
    }
}