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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\rH\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010 \u001a\u00020\u000b2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\tH\u00d6\u0001J\t\u0010#\u001a\u00020\rH\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006$"}, d2 = {"Lcom/skillswap/ai/ui/dashboard/DashboardUiState;", "", "currentUser", "Lcom/skillswap/ai/data/model/User;", "allUsers", "", "recentRequests", "Lcom/skillswap/ai/data/model/ExchangeRequest;", "unreadNotifications", "", "isLoading", "", "error", "", "(Lcom/skillswap/ai/data/model/User;Ljava/util/List;Ljava/util/List;IZLjava/lang/String;)V", "getAllUsers", "()Ljava/util/List;", "getCurrentUser", "()Lcom/skillswap/ai/data/model/User;", "getError", "()Ljava/lang/String;", "()Z", "getRecentRequests", "getUnreadNotifications", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DashboardUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.skillswap.ai.data.model.User currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.User> allUsers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> recentRequests = null;
    private final int unreadNotifications = 0;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public DashboardUiState(@org.jetbrains.annotations.Nullable()
    com.skillswap.ai.data.model.User currentUser, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> allUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> recentRequests, int unreadNotifications, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skillswap.ai.data.model.User getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> getAllUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> getRecentRequests() {
        return null;
    }
    
    public final int getUnreadNotifications() {
        return 0;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public DashboardUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skillswap.ai.data.model.User component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.dashboard.DashboardUiState copy(@org.jetbrains.annotations.Nullable()
    com.skillswap.ai.data.model.User currentUser, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> allUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> recentRequests, int unreadNotifications, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}