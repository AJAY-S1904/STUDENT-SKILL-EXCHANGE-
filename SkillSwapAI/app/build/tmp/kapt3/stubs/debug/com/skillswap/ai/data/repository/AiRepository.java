package com.skillswap.ai.data.repository;

import com.skillswap.ai.data.model.AiMatchRequest;
import com.skillswap.ai.data.model.AiMatchResponse;
import com.skillswap.ai.data.remote.AiApiService;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/skillswap/ai/data/repository/AiRepository;", "", "aiApiService", "Lcom/skillswap/ai/data/remote/AiApiService;", "(Lcom/skillswap/ai/data/remote/AiApiService;)V", "getSkillMatch", "Lcom/skillswap/ai/data/repository/AuthResult;", "Lcom/skillswap/ai/data/model/AiMatchResponse;", "request", "Lcom/skillswap/ai/data/model/AiMatchRequest;", "(Lcom/skillswap/ai/data/model/AiMatchRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isApiHealthy", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AiRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.remote.AiApiService aiApiService = null;
    
    @javax.inject.Inject()
    public AiRepository(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.remote.AiApiService aiApiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSkillMatch(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.AiMatchRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<com.skillswap.ai.data.model.AiMatchResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isApiHealthy(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}