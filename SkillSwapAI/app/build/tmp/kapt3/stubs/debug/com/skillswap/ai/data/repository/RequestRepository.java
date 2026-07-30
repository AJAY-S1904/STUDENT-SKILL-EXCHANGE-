package com.skillswap.ai.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skillswap.ai.data.model.ExchangeRequest;
import com.skillswap.ai.data.model.RequestStatus;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e2\u0006\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/skillswap/ai/data/repository/RequestRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "requestsRef", "Lcom/google/firebase/database/DatabaseReference;", "getReceivedRequests", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/skillswap/ai/data/model/ExchangeRequest;", "userId", "", "getRequest", "Lcom/skillswap/ai/data/repository/AuthResult;", "requestId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSentRequests", "sendRequest", "request", "(Lcom/skillswap/ai/data/model/ExchangeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRequest", "", "updateRequestStatus", "status", "Lcom/skillswap/ai/data/model/RequestStatus;", "(Ljava/lang/String;Lcom/skillswap/ai/data/model/RequestStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RequestRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference requestsRef = null;
    
    @javax.inject.Inject()
    public RequestRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendRequest(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.ExchangeRequest>> getSentRequests(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.ExchangeRequest>> getReceivedRequests(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRequestStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.RequestStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRequest(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.ExchangeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<com.skillswap.ai.data.model.ExchangeRequest>> $completion) {
        return null;
    }
}