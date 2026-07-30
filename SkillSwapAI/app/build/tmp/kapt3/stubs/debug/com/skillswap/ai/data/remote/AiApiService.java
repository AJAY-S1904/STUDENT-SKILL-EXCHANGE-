package com.skillswap.ai.data.remote;

import com.skillswap.ai.data.model.AiMatchRequest;
import com.skillswap.ai.data.model.AiMatchResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/skillswap/ai/data/remote/AiApiService;", "", "getSkillMatch", "Lretrofit2/Response;", "Lcom/skillswap/ai/data/model/AiMatchResponse;", "request", "Lcom/skillswap/ai/data/model/AiMatchRequest;", "(Lcom/skillswap/ai/data/model/AiMatchRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "healthCheck", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AiApiService {
    
    @retrofit2.http.POST(value = "match")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSkillMatch(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.AiMatchRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.skillswap.ai.data.model.AiMatchResponse>> $completion);
    
    @retrofit2.http.GET(value = "health")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object healthCheck(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
}