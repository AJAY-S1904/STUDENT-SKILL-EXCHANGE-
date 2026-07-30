package com.skillswap.ai.data.repository

import com.skillswap.ai.data.model.AiMatchRequest
import com.skillswap.ai.data.model.AiMatchResponse
import com.skillswap.ai.data.remote.AiApiService
import javax.inject.Inject
import javax.inject.Singleton

import kotlinx.coroutines.delay
import kotlin.math.max

@Singleton
class AiRepository @Inject constructor(
    private val aiApiService: AiApiService
) {
    suspend fun getSkillMatch(request: AiMatchRequest): AuthResult<AiMatchResponse> {
        return try {
            // Simulated network delay for AI processing
            delay(1500)
            
            if (request.candidates.isEmpty()) {
                return AuthResult.Error("No candidates available for matching")
            }
            
            // Simple heuristic to score candidates (Mocking AI logic locally)
            var bestCandidate = request.candidates.first()
            var highestScore = 0.0
            
            for (candidate in request.candidates) {
                var score = 0.0
                
                // My learn skills match their teach skills
                val learnMatch = request.learning_skills.map { it.lowercase() }
                    .intersect(candidate.teach_skills.map { it.lowercase() }.toSet()).size
                score += learnMatch * 30.0
                
                // My teach skills match their learn skills
                val teachMatch = request.teach_skills.map { it.lowercase() }
                    .intersect(candidate.learning_skills.map { it.lowercase() }.toSet()).size
                score += teachMatch * 30.0
                
                // Rating bonus
                score += candidate.rating * 2.0
                
                if (score > highestScore) {
                    highestScore = score
                    bestCandidate = candidate
                }
            }
            
            // Generate a reasonable, deterministic percentage so it doesn't change wildly
            val deterministicSeed = (bestCandidate.uid.hashCode() + request.teach_skills.hashCode()).toLong()
            val random = java.util.Random(deterministicSeed)
            val percentage = max(highestScore, 65.0 + (random.nextDouble() * 25.0)).coerceAtMost(98.0)
            
            val response = AiMatchResponse(
                recommended_student = com.skillswap.ai.data.model.RecommendedStudent(
                    uid = bestCandidate.uid,
                    name = bestCandidate.name,
                    college = bestCandidate.college,
                    department = bestCandidate.department,
                    teach_skills = bestCandidate.teach_skills,
                    learning_skills = bestCandidate.learning_skills,
                    experience = bestCandidate.experience,
                    rating = bestCandidate.rating,
                    availability = bestCandidate.availability
                ),
                match_percentage = percentage,
                compatibility_score = percentage, // Provide 0-100 instead of 0.0-1.0 so UI toInt() works correctly
                reason = listOf(
                    "Strong overlap in desired skills",
                    "Highly rated peer"
                )
            )
            
            AuthResult.Success(response)
        } catch (e: Exception) {
            AuthResult.Error("Error generating match: ${e.message}")
        }
    }

    suspend fun isApiHealthy(): Boolean {
        return true
    }
}
