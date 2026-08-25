package com.skillswap.ai.ui.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.AiCareerAnalysis
import com.skillswap.ai.data.repository.FirestoreRepository
import com.skillswap.ai.data.repository.GeminiRepository
import com.skillswap.ai.data.repository.UserRepository
import com.skillswap.ai.data.repository.AuthResult
import com.skillswap.ai.data.model.SkillVerification
import com.skillswap.ai.data.model.VerificationStatus
import com.skillswap.ai.data.model.VerificationMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiFeaturesViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository,
    private val firestoreRepository: FirestoreRepository,
    private val authRepository: com.skillswap.ai.data.repository.AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    
    val currentUserId: String get() = authRepository.currentUserId

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentAnalysis = MutableStateFlow<AiCareerAnalysis?>(null)
    val currentAnalysis: StateFlow<AiCareerAnalysis?> = _currentAnalysis.asStateFlow()

    private val _analysisHistory = MutableStateFlow<List<AiCareerAnalysis>>(emptyList())
    val analysisHistory: StateFlow<List<AiCareerAnalysis>> = _analysisHistory.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun clearError() {
        _error.value = null
    }

    fun generateFullCareerAnalysis(
        careerGoal: String,
        targetSkill: String,
        currentSkills: List<String>
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                // 1. Generate Skill Gap Analysis
                val gapResult = geminiRepository.generateSkillGapAnalysis(currentSkills, targetSkill, careerGoal)
                if (gapResult == null) {
                    _error.value = "Failed to generate skill gap analysis."
                    _isLoading.value = false
                    return@launch
                }
                
                // 2. Generate Learning Roadmap
                val roadmapResult = geminiRepository.generateLearningRoadmap(
                    careerGoal = careerGoal,
                    targetSkill = targetSkill,
                    currentSkills = currentSkills,
                    missingSkills = gapResult.missingSkills
                )
                if (roadmapResult == null) {
                    _error.value = "Failed to generate learning roadmap."
                    _isLoading.value = false
                    return@launch
                }
                
                // 3. Save Unified Result
                val analysisId = java.util.UUID.randomUUID().toString()
                
                val analysis = AiCareerAnalysis(
                    id = analysisId,
                    userId = currentUserId,
                    careerGoal = careerGoal,
                    targetSkill = targetSkill,
                    currentSkills = currentSkills,
                    overallReadiness = 100 - gapResult.gapPercentage,
                    skillGap = gapResult.gapPercentage,
                    strengths = gapResult.strengths,
                    weaknesses = gapResult.weaknesses,
                    missingSkills = gapResult.missingSkills,
                    recommendations = gapResult.recommendations
                )
                
                val roadmap = com.skillswap.ai.data.model.LearningRoadmap(
                    id = java.util.UUID.randomUUID().toString(),
                    analysisId = analysisId,
                    userId = currentUserId,
                    careerGoal = careerGoal,
                    targetSkill = targetSkill,
                    difficultyLevel = roadmapResult.difficultyLevel,
                    overallEstimatedWeeks = roadmapResult.overallEstimatedWeeks,
                    stages = roadmapResult.stages.map { stage ->
                        com.skillswap.ai.data.model.LearningStage(
                            title = stage.title,
                            estimatedDuration = stage.estimatedDuration,
                            description = stage.description,
                            highPriorityTopics = stage.highPriorityTopics.map { "🔥 High: $it" },
                            mediumPriorityTopics = stage.mediumPriorityTopics.map { "🟡 Medium: $it" },
                            advancedTopics = stage.advancedTopics.map { "🟢 Advanced: $it" }
                        )
                    }
                )
                
                firestoreRepository.saveAiCareerAnalysis(analysis)
                firestoreRepository.saveLearningRoadmap(roadmap)
                
                _currentAnalysis.value = analysis
                _analysisHistory.value = listOf(analysis) + _analysisHistory.value
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "An error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAnalysisHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val history = firestoreRepository.getAiCareerAnalyses(currentUserId)
                _analysisHistory.value = history.sortedByDescending { it.createdAt }
            } catch (e: Exception) {
                _error.value = "Failed to load history: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteAnalysis(analysisId: String) {
        viewModelScope.launch {
            try {
                firestoreRepository.deleteAiCareerAnalysis(analysisId)
                _analysisHistory.value = _analysisHistory.value.filter { it.id != analysisId }
            } catch (e: Exception) {
                _error.value = "Failed to delete analysis: ${e.message}"
            }
        }
    }

    fun loadAnalysis(analysisId: String?) {
        if (analysisId.isNullOrEmpty()) {
            _currentAnalysis.value = null
        } else {
            _currentAnalysis.value = _analysisHistory.value.find { it.id == analysisId }
        }
    }

    private val _mcqTest = MutableStateFlow<com.skillswap.ai.data.model.McqTest?>(null)
    val mcqTest: StateFlow<com.skillswap.ai.data.model.McqTest?> = _mcqTest.asStateFlow()
    
    private val _roadmapHistory = MutableStateFlow<List<com.skillswap.ai.data.model.LearningRoadmap>>(emptyList())
    val roadmapHistory: StateFlow<List<com.skillswap.ai.data.model.LearningRoadmap>> = _roadmapHistory.asStateFlow()
    
    private val _currentRoadmap = MutableStateFlow<com.skillswap.ai.data.model.LearningRoadmap?>(null)
    val currentRoadmap: StateFlow<com.skillswap.ai.data.model.LearningRoadmap?> = _currentRoadmap.asStateFlow()

    fun loadRoadmapHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val history = firestoreRepository.getLearningRoadmaps(currentUserId)
                _roadmapHistory.value = history.sortedByDescending { it.timestamp }
            } catch (e: Exception) {
                _error.value = "Failed to load roadmaps: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadRoadmapByAnalysisId(analysisId: String?) {
        viewModelScope.launch {
            if (analysisId.isNullOrEmpty()) {
                _currentRoadmap.value = null
                return@launch
            }
            _isLoading.value = true
            try {
                _currentRoadmap.value = firestoreRepository.getLearningRoadmapByAnalysisId(analysisId, currentUserId)
            } catch (e: Exception) {
                _error.value = "Failed to load roadmap: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun regenerateRoadmapForAnalysis(analysisId: String) {
        val analysis = _analysisHistory.value.find { it.id == analysisId } ?: return
        
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                val roadmapResult = geminiRepository.generateLearningRoadmap(
                    careerGoal = analysis.careerGoal,
                    targetSkill = analysis.targetSkill,
                    currentSkills = analysis.currentSkills,
                    missingSkills = analysis.missingSkills
                )
                
                if (roadmapResult == null) {
                    _error.value = "Failed to generate learning roadmap."
                    _isLoading.value = false
                    return@launch
                }
                
                val roadmap = com.skillswap.ai.data.model.LearningRoadmap(
                    id = java.util.UUID.randomUUID().toString(),
                    analysisId = analysisId,
                    userId = currentUserId,
                    careerGoal = analysis.careerGoal,
                    targetSkill = analysis.targetSkill,
                    difficultyLevel = roadmapResult.difficultyLevel,
                    overallEstimatedWeeks = roadmapResult.overallEstimatedWeeks,
                    stages = roadmapResult.stages.map { stage ->
                        com.skillswap.ai.data.model.LearningStage(
                            title = stage.title,
                            estimatedDuration = stage.estimatedDuration,
                            description = stage.description,
                            highPriorityTopics = stage.highPriorityTopics.map { "🔥 High: $it" },
                            mediumPriorityTopics = stage.mediumPriorityTopics.map { "🟡 Medium: $it" },
                            advancedTopics = stage.advancedTopics.map { "🟢 Advanced: $it" }
                        )
                    }
                )
                
                firestoreRepository.saveLearningRoadmap(roadmap)
                _currentRoadmap.value = roadmap
                
            } catch (e: Exception) {
                _error.value = "An error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun deleteRoadmap(roadmapId: String) {
        viewModelScope.launch {
            try {
                firestoreRepository.deleteLearningRoadmap(roadmapId)
                _roadmapHistory.value = _roadmapHistory.value.filter { it.id != roadmapId }
            } catch (e: Exception) {
                _error.value = "Failed to delete roadmap: ${e.message}"
            }
        }
    }

    fun generateMcqTest(skill: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val test = geminiRepository.generateMcqTest(skill)
            if (test != null) {
                _mcqTest.value = test
            } else {
                _error.value = "Failed to generate MCQ test. Please try again."
            }

            _isLoading.value = false
        }
    }

    fun submitMcqTest(skill: String, score: Int, total: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val percentage = if (total > 0) (score.toFloat() / total) * 100 else 0f
            val passed = percentage >= 70f
            
            val status = if (passed) VerificationStatus.VERIFIED else VerificationStatus.REJECTED
            val verification = SkillVerification(
                userId = currentUserId,
                skillName = skill,
                status = status,
                method = VerificationMethod.MCQ_TEST,
                mcqScore = percentage.toInt()
            )
            
            try {
                firestoreRepository.saveSkillVerification(verification)
                if (passed) {
                    val userResult = userRepository.getUser(currentUserId)
                    if (userResult is AuthResult.Success) {
                        val currentSkills = userResult.data.teachSkills
                        val verifiedSkills = userResult.data.verifiedSkills
                        val updates = mutableMapOf<String, Any>()
                        
                        if (!currentSkills.contains(skill)) {
                            updates["teachSkills"] = currentSkills + skill
                        }
                        if (!verifiedSkills.contains(skill)) {
                            updates["verifiedSkills"] = verifiedSkills + skill
                        }
                        
                        if (updates.isNotEmpty()) {
                            userRepository.updateUser(currentUserId, updates)
                        }
                    }
                }
            } catch (e: Exception) {
                _error.value = "Failed to save verification result: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
