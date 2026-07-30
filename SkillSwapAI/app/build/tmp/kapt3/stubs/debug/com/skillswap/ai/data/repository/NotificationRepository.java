package com.skillswap.ai.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skillswap.ai.data.model.AppNotification;
import com.skillswap.ai.data.model.NotificationType;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00140\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J.\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001dJ.\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/skillswap/ai/data/repository/NotificationRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "notifRef", "Lcom/google/firebase/database/DatabaseReference;", "createNotification", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "notification", "Lcom/skillswap/ai/data/model/AppNotification;", "(Lcom/skillswap/ai/data/model/AppNotification;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnreadCount", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "markAllAsRead", "markAsRead", "notificationId", "sendAcceptedNotification", "receiverId", "acceptorName", "acceptorPic", "requestId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendRequestNotification", "senderName", "senderPic", "app_debug"})
public final class NotificationRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference notifRef = null;
    
    @javax.inject.Inject()
    public NotificationRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.AppNotification>> getUserNotifications(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createNotification(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.AppNotification notification, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String notificationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markAllAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUnreadCount(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendRequestNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String receiverId, @org.jetbrains.annotations.NotNull()
    java.lang.String senderName, @org.jetbrains.annotations.NotNull()
    java.lang.String senderPic, @org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendAcceptedNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String receiverId, @org.jetbrains.annotations.NotNull()
    java.lang.String acceptorName, @org.jetbrains.annotations.NotNull()
    java.lang.String acceptorPic, @org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}