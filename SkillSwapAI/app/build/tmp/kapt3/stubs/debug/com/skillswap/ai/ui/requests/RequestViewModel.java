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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0015J\b\u0010%\u001a\u00020\u001dH\u0002J\u000e\u0010&\u001a\u00020\u001d2\u0006\u0010\'\u001a\u00020\u0015J\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0015J6\u0010)\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0015J\u000e\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201J6\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0015R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/skillswap/ai/ui/requests/RequestViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "requestRepository", "Lcom/skillswap/ai/data/repository/RequestRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "notificationRepository", "Lcom/skillswap/ai/data/repository/NotificationRepository;", "creditRepository", "Lcom/skillswap/ai/data/repository/CreditRepository;", "sessionRepository", "Lcom/skillswap/ai/data/repository/SessionRepository;", "meetingRepository", "Lcom/skillswap/ai/data/repository/MeetingRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/RequestRepository;Lcom/skillswap/ai/data/repository/UserRepository;Lcom/skillswap/ai/data/repository/NotificationRepository;Lcom/skillswap/ai/data/repository/CreditRepository;Lcom/skillswap/ai/data/repository/SessionRepository;Lcom/skillswap/ai/data/repository/MeetingRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/requests/RequestsUiState;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "acceptMeeting", "", "meeting", "Lcom/skillswap/ai/data/model/MeetingRequest;", "request", "Lcom/skillswap/ai/data/model/ExchangeRequest;", "acceptRequest", "cancelRequest", "requestId", "loadRequests", "rejectMeeting", "meetingId", "rejectRequest", "scheduleMeeting", "date", "time", "mode", "locationOrLink", "notes", "selectTab", "tab", "", "sendRequest", "receiverId", "receiverName", "receiverProfilePic", "teachSkill", "learnSkill", "message", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RequestViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.RequestRepository requestRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.NotificationRepository notificationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.CreditRepository creditRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.MeetingRepository meetingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.requests.RequestsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.requests.RequestsUiState> uiState = null;
    
    @javax.inject.Inject()
    public RequestViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.RequestRepository requestRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.NotificationRepository notificationRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.CreditRepository creditRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.SessionRepository sessionRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.MeetingRepository meetingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.requests.RequestsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    private final void loadRequests() {
    }
    
    public final void sendRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String receiverId, @org.jetbrains.annotations.NotNull()
    java.lang.String receiverName, @org.jetbrains.annotations.NotNull()
    java.lang.String receiverProfilePic, @org.jetbrains.annotations.NotNull()
    java.lang.String teachSkill, @org.jetbrains.annotations.NotNull()
    java.lang.String learnSkill, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void acceptRequest(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request) {
    }
    
    public final void rejectRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String requestId) {
    }
    
    public final void scheduleMeeting(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String mode, @org.jetbrains.annotations.NotNull()
    java.lang.String locationOrLink, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
    }
    
    public final void acceptMeeting(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.MeetingRequest meeting, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request) {
    }
    
    public final void rejectMeeting(@org.jetbrains.annotations.NotNull()
    java.lang.String meetingId) {
    }
    
    public final void cancelRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String requestId) {
    }
    
    public final void selectTab(int tab) {
    }
}