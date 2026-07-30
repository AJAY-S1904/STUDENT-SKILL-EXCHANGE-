package com.skillswap.ai.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/skillswap/ai/service/SkillSwapFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "getUserRepository", "()Lcom/skillswap/ai/data/repository/UserRepository;", "setUserRepository", "(Lcom/skillswap/ai/data/repository/UserRepository;)V", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "showNotification", "title", "body", "app_debug"})
public final class SkillSwapFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @javax.inject.Inject()
    public com.skillswap.ai.data.repository.UserRepository userRepository;
    
    public SkillSwapFirebaseMessagingService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.data.repository.UserRepository getUserRepository() {
        return null;
    }
    
    public final void setUserRepository(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository p0) {
    }
    
    @java.lang.Override()
    public void onNewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage remoteMessage) {
    }
    
    private final void showNotification(java.lang.String title, java.lang.String body) {
    }
}