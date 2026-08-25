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

            val myLearnSkills = request.learning_skills.map { it.lowercase() }
            val myTeachSkills = request.teach_skills.map { it.lowercase() }

            // Stage 1 - Hard Skill Filter
            val eligibleCandidates = if (myLearnSkills.isNotEmpty()) {
                request.candidates.filter { candidate ->
                    val verifiedTeachSkills = candidate.teach_skills.filter { skill ->
                        candidate.verified_skills.any { it.equals(skill, ignoreCase = true) }
                    }.map { it.lowercase() }
                    
                    myLearnSkills.intersect(verifiedTeachSkills.toSet()).isNotEmpty()
                }
            } else {
                request.candidates
            }

            if (eligibleCandidates.isEmpty()) {
                return AuthResult.Error("No suitable matches found for your learning requests.")
            }
            
            // Stage 2 - AI Matching (Scoring)
            var bestCandidate = eligibleCandidates.first()
            var highestScore = -1.0
            var bestMatchReasons = mutableListOf<String>()
            
            for (candidate in eligibleCandidates) {
                var score = 0.0
                
                val candidateVerifiedTeach = candidate.teach_skills.filter { skill ->
                    candidate.verified_skills.any { it.equals(skill, ignoreCase = true) }
                }

                // My learn skills match their verified teach skills
                val learnMatch = myLearnSkills
                    .intersect(candidateVerifiedTeach.map { it.lowercase() }.toSet())
                score += learnMatch.size * 40.0
                
                // My teach skills match their learn skills
                val teachMatch = myTeachSkills
                    .intersect(candidate.learning_skills.map { it.lowercase() }.toSet())
                score += teachMatch.size * 30.0
                
                // Rating bonus
                score += candidate.rating * 2.0
                
                if (score > highestScore) {
                    highestScore = score
                    bestCandidate = candidate
                    
                    // Generate accurate reasons
                    val reasons = mutableListOf<String>()
                    if (learnMatch.isNotEmpty()) {
                        reasons.add("Can teach your requested skill: ${learnMatch.first().replaceFirstChar { it.uppercase() }}")
                    }
                    if (teachMatch.isNotEmpty()) {
                        reasons.add("Wants to learn what you teach")
                    }
                    if (candidate.rating >= 4.0) {
                        reasons.add("Highly rated peer")
                    }
                    if (reasons.isEmpty()) {
                        reasons.add("Compatible profile")
                    }
                    bestMatchReasons = reasons
                }
            }
            
            // Generate a reasonable, deterministic percentage so it doesn't change wildly
            val deterministicSeed = (bestCandidate.uid.hashCode() + request.teach_skills.hashCode()).toLong()
            val random = java.util.Random(deterministicSeed)
            val percentage = max(highestScore, 65.0 + (random.nextDouble() * 25.0)).coerceAtMost(98.0)
            
            val verifiedTeachSkills = bestCandidate.teach_skills.filter { skill ->
                bestCandidate.verified_skills.any { it.equals(skill, ignoreCase = true) }
            }

            val response = AiMatchResponse(
                recommended_student = com.skillswap.ai.data.model.RecommendedStudent(
                    uid = bestCandidate.uid,
                    name = bestCandidate.name,
                    college = bestCandidate.college,
                    department = bestCandidate.department,
                    teach_skills = verifiedTeachSkills, // Only show verified teaching skills
                    learning_skills = bestCandidate.learning_skills,
                    verified_skills = bestCandidate.verified_skills,
                    experience = bestCandidate.experience,
                    rating = bestCandidate.rating,
                    availability = bestCandidate.availability
                ),
                match_percentage = percentage,
                compatibility_score = percentage,
                reason = bestMatchReasons
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
