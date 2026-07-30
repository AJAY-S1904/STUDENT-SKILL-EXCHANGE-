package com.skillswap.ai.ui.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.AiMatchRequest
import com.skillswap.ai.data.model.AiMatchResponse
import com.skillswap.ai.data.model.CandidateProfile
import com.skillswap.ai.data.repository.AiRepository
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.AuthResult
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AiMatchingUiState(
    val currentUserName: String = "",
    val currentDepartment: String = "",
    val teachSkills: List<String> = emptyList(),
    val learnSkills: List<String> = emptyList(),
    val experience: String = "Beginner",
    val rating: Double = 0.0,
    val availability: List<String> = emptyList(),
    val aiResponse: AiMatchResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasMatched: Boolean = false
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class AiMatchingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AiMatchingUiState())
    val uiState: StateFlow<AiMatchingUiState> = _uiState.asStateFlow()

    val currentUserId: String get() = authRepository.currentUserId

    init {
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) kotlinx.coroutines.flow.flowOf(null) else userRepository.getUserFlow(uid)
            }.collect { user ->
                _uiState.update {
                    it.copy(
                        currentUserName = user?.name ?: "",
                        currentDepartment = user?.department ?: "",
                        teachSkills = user?.teachSkills ?: emptyList(),
                        learnSkills = user?.learnSkills ?: emptyList(),
                        experience = user?.experienceLevel ?: "Beginner",
                        rating = user?.rating ?: 0.0,
                        availability = user?.availability ?: emptyList()
                    )
                }
            }
        }
    }

    fun findBestMatch() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState.currentDepartment.isEmpty()) {
                _uiState.update { it.copy(error = "User profile is still loading. Please try again in a moment.") }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true, error = null, aiResponse = null) }

            // Build candidates list from other users
            val candidatesResult = userRepository.getAllUsers()
            val candidates = when (candidatesResult) {
                is AuthResult.Success -> candidatesResult.data
                    .filter { it.uid != authRepository.currentUserId }
                    .map { user ->
                        CandidateProfile(
                            uid = user.uid,
                            name = user.name,
                            teach_skills = user.teachSkills,
                            learning_skills = user.learnSkills,
                            experience = user.experienceLevel,
                            rating = user.rating,
                            availability = user.availability,
                            college = user.college,
                            department = user.department
                        )
                    }
                else -> emptyList()
            }

            if (candidates.isEmpty()) {
                _uiState.update { it.copy(isLoading = false, error = "No other students found to match with.") }
                return@launch
            }

            val state = _uiState.value
            val request = AiMatchRequest(
                teach_skills = state.teachSkills,
                learning_skills = state.learnSkills,
                experience = state.experience,
                rating = state.rating,
                availability = state.availability,
                candidates = candidates
            )

            when (val result = aiRepository.getSkillMatch(request)) {
                is AuthResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, aiResponse = result.data, hasMatched = true)
                    }
                }
                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun reset() {
        _uiState.update { it.copy(aiResponse = null, hasMatched = false, error = null) }
    }
}
