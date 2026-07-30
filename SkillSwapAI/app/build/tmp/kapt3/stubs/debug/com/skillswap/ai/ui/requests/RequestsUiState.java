package com.skillswap.ai.ui.requests;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.ExchangeRequest;
import com.skillswap.ai.data.model.RequestStatus;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import com.skillswap.ai.data.repository.RequestRepository;
import com.skillswap.ai.data.repository.MeetingRepository;
import com.skillswap.ai.data.repository.SessionRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000fJ\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rH\u00c6\u0003Je\u0010!\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u000bH\u00d6\u0001J\t\u0010%\u001a\u00020\rH\u00d6\u0001R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011\u00a8\u0006&"}, d2 = {"Lcom/skillswap/ai/ui/requests/RequestsUiState;", "", "sentRequests", "", "Lcom/skillswap/ai/data/model/ExchangeRequest;", "receivedRequests", "meetingRequests", "Lcom/skillswap/ai/data/model/MeetingRequest;", "isLoading", "", "selectedTab", "", "successMessage", "", "error", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;ZILjava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "()Z", "getMeetingRequests", "()Ljava/util/List;", "getReceivedRequests", "getSelectedTab", "()I", "getSentRequests", "getSuccessMessage", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class RequestsUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> sentRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> receivedRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.MeetingRequest> meetingRequests = null;
    private final boolean isLoading = false;
    private final int selectedTab = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String successMessage = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public RequestsUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> sentRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> receivedRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.MeetingRequest> meetingRequests, boolean isLoading, int selectedTab, @org.jetbrains.annotations.Nullable()
    java.lang.String successMessage, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> getSentRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> getReceivedRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.MeetingRequest> getMeetingRequests() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final int getSelectedTab() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSuccessMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public RequestsUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.ExchangeRequest> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.MeetingRequest> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.requests.RequestsUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> sentRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.ExchangeRequest> receivedRequests, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.MeetingRequest> meetingRequests, boolean isLoading, int selectedTab, @org.jetbrains.annotations.Nullable()
    java.lang.String successMessage, @org.jetbrains.annotations.Nullable()
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