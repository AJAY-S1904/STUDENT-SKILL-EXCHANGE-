package com.skillswap.ai.data.remote

import com.skillswap.ai.data.model.AiMatchRequest
import com.skillswap.ai.data.model.AiMatchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AiApiService {

    @POST("match")
    suspend fun getSkillMatch(
        @Body request: AiMatchRequest
    ): Response<AiMatchResponse>

    @GET("health")
    suspend fun healthCheck(): Response<Map<String, String>>
}
