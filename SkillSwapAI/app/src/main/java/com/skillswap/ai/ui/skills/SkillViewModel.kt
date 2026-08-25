package com.skillswap.ai.ui.skills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SkillUiState(
    val teachSkills: List<String> = emptyList(),
    val learnSkills: List<String> = emptyList(),
    val verifiedSkills: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val successMessage: String? = null
)

@HiltViewModel
class SkillViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SkillUiState())
    val uiState: StateFlow<SkillUiState> = _uiState.asStateFlow()

    init {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            userRepository.getUserFlow(uid).collect { user ->
                _uiState.update {
                    it.copy(
                        teachSkills = user?.teachSkills ?: emptyList(),
                        learnSkills = user?.learnSkills ?: emptyList(),
                        verifiedSkills = user?.verifiedSkills ?: emptyList()
                    )
                }
            }
        }
    }

    fun addTeachSkill(skill: String) {
        if (skill.isBlank()) return
        val updated = (_uiState.value.teachSkills + skill.trim()).distinct()
        saveTeachSkills(updated)
    }

    fun removeTeachSkill(skill: String) {
        val updated = _uiState.value.teachSkills - skill
        saveTeachSkills(updated)
    }

    fun addLearnSkill(skill: String) {
        if (skill.isBlank()) return
        val updated = (_uiState.value.learnSkills + skill.trim()).distinct()
        saveLearnSkills(updated)
    }

    fun removeLearnSkill(skill: String) {
        val updated = _uiState.value.learnSkills - skill
        saveLearnSkills(updated)
    }

    private fun saveTeachSkills(skills: List<String>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.updateTeachSkills(authRepository.currentUserId, skills)
            _uiState.update { it.copy(isLoading = false, successMessage = "Teaching skills updated!") }
        }
    }

    private fun saveLearnSkills(skills: List<String>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.updateLearnSkills(authRepository.currentUserId, skills)
            _uiState.update { it.copy(isLoading = false, successMessage = "Learning skills updated!") }
        }
    }
}
