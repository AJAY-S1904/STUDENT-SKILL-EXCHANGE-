package com.skillswap.ai.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skillswap.ai.data.model.MeetingRequest;
import com.skillswap.ai.data.model.MeetingStatus;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\tJ$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/skillswap/ai/data/repository/MeetingRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "meetingsRef", "Lcom/google/firebase/database/DatabaseReference;", "createMeetingRequest", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "request", "Lcom/skillswap/ai/data/model/MeetingRequest;", "(Lcom/skillswap/ai/data/model/MeetingRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMeetingRequestsForUser", "Lkotlinx/coroutines/flow/Flow;", "", "userId", "updateMeetingStatus", "", "meetingId", "status", "Lcom/skillswap/ai/data/model/MeetingStatus;", "(Ljava/lang/String;Lcom/skillswap/ai/data/model/MeetingStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MeetingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference meetingsRef = null;
    
    @javax.inject.Inject()
    public MeetingRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createMeetingRequest(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.MeetingRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.MeetingRequest>> getMeetingRequestsForUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMeetingStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String meetingId, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.MeetingStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
}