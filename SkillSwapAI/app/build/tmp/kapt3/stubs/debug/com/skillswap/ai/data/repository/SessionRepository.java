package com.skillswap.ai.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skillswap.ai.data.model.Session;
import com.skillswap.ai.data.model.SessionStatus;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\tJ\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\tJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\b2\u0006\u0010\u0015\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\b2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\b2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/skillswap/ai/data/repository/SessionRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "sessionsRef", "Lcom/google/firebase/database/DatabaseReference;", "createSession", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "session", "Lcom/skillswap/ai/data/model/Session;", "(Lcom/skillswap/ai/data/model/Session;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLearnerSessions", "Lkotlinx/coroutines/flow/Flow;", "", "learnerId", "getTeacherSessions", "teacherId", "markSessionComplete", "", "sessionId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleSession", "date", "time", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotes", "notes", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SessionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference sessionsRef = null;
    
    @javax.inject.Inject()
    public SessionRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createSession(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.Session session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.Session>> getTeacherSessions(@org.jetbrains.annotations.NotNull()
    java.lang.String teacherId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.Session>> getLearnerSessions(@org.jetbrains.annotations.NotNull()
    java.lang.String learnerId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object scheduleSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markSessionComplete(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
}