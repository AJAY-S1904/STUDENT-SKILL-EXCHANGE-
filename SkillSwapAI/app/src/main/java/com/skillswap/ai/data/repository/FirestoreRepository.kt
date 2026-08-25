package com.skillswap.ai.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.skillswap.ai.data.model.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    // --- AI Career Analysis ---
    suspend fun saveAiCareerAnalysis(analysis: AiCareerAnalysis) {
        val id = analysis.id.ifEmpty { firestore.collection("ai_career_analyses").document().id }
        firestore.collection("ai_career_analyses")
            .document(id)
            .set(analysis.copy(id = id))
            .await()
    }

    suspend fun getAiCareerAnalyses(userId: String): List<AiCareerAnalysis> {
        val snapshot = firestore.collection("ai_career_analyses")
            .whereEqualTo("userId", userId)
            .get()
            .await()
        return snapshot.toObjects(AiCareerAnalysis::class.java)
    }

    suspend fun deleteAiCareerAnalysis(analysisId: String) {
        firestore.collection("ai_career_analyses")
            .document(analysisId)
            .delete()
            .await()
    }

    // --- Learning Roadmap ---
    suspend fun saveLearningRoadmap(roadmap: LearningRoadmap) {
        val id = roadmap.id.ifEmpty { firestore.collection("learning_roadmaps").document().id }
        firestore.collection("learning_roadmaps")
            .document(id)
            .set(roadmap.copy(id = id))
            .await()
    }

    suspend fun getLearningRoadmaps(userId: String): List<LearningRoadmap> {
        migrateOldAnalysesToRoadmaps(userId)
        val snapshot = firestore.collection("learning_roadmaps")
            .whereEqualTo("userId", userId)
            .get()
            .await()
        return snapshot.toObjects(LearningRoadmap::class.java)
    }
    
    suspend fun getLearningRoadmapByAnalysisId(analysisId: String, userId: String): LearningRoadmap? {
        migrateOldAnalysesToRoadmaps(userId)
        val snapshot = firestore.collection("learning_roadmaps")
            .whereEqualTo("analysisId", analysisId)
            .whereEqualTo("userId", userId)
            .get()
            .await()
        return snapshot.toObjects(LearningRoadmap::class.java).firstOrNull()
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun migrateOldAnalysesToRoadmaps(userId: String) {
        try {
            val analysesSnapshot = firestore.collection("ai_career_analyses")
                .whereEqualTo("userId", userId)
                .get()
                .await()
                
            val roadmapsSnapshot = firestore.collection("learning_roadmaps")
                .whereEqualTo("userId", userId)
                .get()
                .await()
                
            val existingAnalysisIds = roadmapsSnapshot.documents.mapNotNull { it.getString("analysisId") }.toSet()
            
            for (doc in analysesSnapshot.documents) {
                val analysisId = doc.id
                if (!existingAnalysisIds.contains(analysisId) && doc.contains("difficulty")) {
                    val diff = doc.getString("difficulty") ?: ""
                    val estWeeks = doc.getLong("estimatedWeeks")?.toInt() ?: 0
                    val beg = doc.get("beginnerTopics") as? List<String> ?: emptyList()
                    val inter = doc.get("intermediateTopics") as? List<String> ?: emptyList()
                    val adv = doc.get("advancedTopics") as? List<String> ?: emptyList()
                    val goal = doc.getString("careerGoal") ?: ""
                    val target = doc.getString("targetSkill") ?: ""
                    val ts = doc.getLong("createdAt") ?: System.currentTimeMillis()
                    
                    val stages = buildList {
                        if (beg.isNotEmpty()) add(LearningStage("Beginner Stage", "", "Learn the basics.", beg, emptyList(), emptyList()))
                        if (inter.isNotEmpty()) add(LearningStage("Intermediate Stage", "", "Build on fundamentals.", inter, emptyList(), emptyList()))
                        if (adv.isNotEmpty()) add(LearningStage("Advanced Stage", "", "Master complex topics.", adv, emptyList(), emptyList()))
                    }
                    
                    val roadmap = LearningRoadmap(
                        id = java.util.UUID.randomUUID().toString(),
                        userId = userId,
                        analysisId = analysisId,
                        careerGoal = goal,
                        targetSkill = target,
                        overallEstimatedWeeks = estWeeks,
                        difficultyLevel = diff,
                        stages = stages,
                        timestamp = ts
                    )
                    saveLearningRoadmap(roadmap)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("FirestoreRepository", "Migration failed", e)
        }
    }

    suspend fun deleteLearningRoadmap(roadmapId: String) {
        firestore.collection("learning_roadmaps")
            .document(roadmapId)
            .delete()
            .await()
    }

    // --- Skill Verification ---
    suspend fun saveSkillVerification(verification: SkillVerification) {
        val id = verification.id.ifEmpty { firestore.collection("skill_verification").document().id }
        firestore.collection("skill_verification")
            .document(id)
            .set(verification.copy(id = id))
            .await()
    }

    suspend fun getSkillVerifications(userId: String): List<SkillVerification> {
        val snapshot = firestore.collection("skill_verification")
            .whereEqualTo("userId", userId)
            .get()
            .await()
        return snapshot.toObjects(SkillVerification::class.java)
    }

    // --- Learning Session ---
    suspend fun saveLearningSession(session: LearningSession) {
        val id = session.id.ifEmpty { firestore.collection("learning_sessions").document().id }
        firestore.collection("learning_sessions")
            .document(id)
            .set(session.copy(id = id))
            .await()
    }

    suspend fun getLearningSessionsForMeeting(parentMeetingId: String): List<LearningSession> {
        val snapshot = firestore.collection("learning_sessions")
            .whereEqualTo("parentMeetingId", parentMeetingId)
            .get()
            .await()
        return snapshot.toObjects(LearningSession::class.java)
    }

    // --- Skill Portfolio ---
    suspend fun getSkillPortfolio(userId: String): SkillPortfolio? {
        val snapshot = firestore.collection("skill_portfolios")
            .whereEqualTo("userId", userId)
            .get()
            .await()
        return snapshot.toObjects(SkillPortfolio::class.java).firstOrNull()
    }

    suspend fun saveSkillPortfolio(portfolio: SkillPortfolio) {
        val id = portfolio.id.ifEmpty { firestore.collection("skill_portfolios").document().id }
        firestore.collection("skill_portfolios")
            .document(id)
            .set(portfolio.copy(id = id))
            .await()
    }

    suspend fun incrementSessionsCompleted(userId: String) {
        try {
            val portfolio = getSkillPortfolio(userId)
            if (portfolio != null && portfolio.id.isNotEmpty()) {
                firestore.collection("skill_portfolios")
                    .document(portfolio.id)
                    .update("totalSessionsCompleted", com.google.firebase.firestore.FieldValue.increment(1))
                    .await()
            } else {
                val newPortfolio = SkillPortfolio(userId = userId, totalSessionsCompleted = 1)
                saveSkillPortfolio(newPortfolio)
            }
        } catch (e: Exception) {
            android.util.Log.e("FirestoreRepository", "Failed to increment sessions for user $userId", e)
        }
    }
}
