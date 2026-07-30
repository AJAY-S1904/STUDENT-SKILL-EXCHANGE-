package com.skillswap.ai.data.repository;

import com.google.firebase.database.FirebaseDatabase;
import com.skillswap.ai.data.model.Rating;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t2\u0006\u0010\u0016\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\rH\u0082@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/skillswap/ai/data/repository/RatingRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "ratingsRef", "Lcom/google/firebase/database/DatabaseReference;", "usersRef", "getRatingsForUser", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "Lcom/skillswap/ai/data/model/Rating;", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasRatedSession", "", "raterId", "sessionId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitRating", "", "rating", "(Lcom/skillswap/ai/data/model/Rating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserRating", "app_debug"})
public final class RatingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference ratingsRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference usersRef = null;
    
    @javax.inject.Inject()
    public RatingRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitRating(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.Rating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    private final java.lang.Object updateUserRating(java.lang.String userId, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRatingsForUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.Rating>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hasRatedSession(@org.jetbrains.annotations.NotNull()
    java.lang.String raterId, @org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}