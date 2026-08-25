package com.skillswap.ai.ui.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.SkillPortfolio
import com.skillswap.ai.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val authRepository: com.skillswap.ai.data.repository.AuthRepository,
    private val userRepository: com.skillswap.ai.data.repository.UserRepository
) : ViewModel() {
    val currentUserId: String get() = authRepository.currentUserId

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _portfolio = MutableStateFlow<SkillPortfolio?>(null)
    val portfolio: StateFlow<SkillPortfolio?> = _portfolio.asStateFlow()

    private val _user = MutableStateFlow<com.skillswap.ai.data.model.User?>(null)
    val user: StateFlow<com.skillswap.ai.data.model.User?> = _user.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadPortfolio(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                // Fetch portfolio for sessions/certificates
                val result = firestoreRepository.getSkillPortfolio(userId)
                
                // Fetch actual verified skills from RTDB User which is the source of truth
                val userResult = userRepository.getUser(userId)
                val actualVerifiedSkills = if (userResult is com.skillswap.ai.data.repository.AuthResult.Success) {
                    _user.value = userResult.data
                    userResult.data.verifiedSkills
                } else {
                    emptyList()
                }

                if (result != null) {
                    _portfolio.value = result.copy(verifiedSkills = actualVerifiedSkills)
                } else {
                    _portfolio.value = SkillPortfolio(userId = userId, verifiedSkills = actualVerifiedSkills, certificates = emptyList(), totalSessionsCompleted = 0)
                }
            } catch (e: Exception) {
                _error.value = "Failed to load portfolio: ${e.message}"
            }
            
            _isLoading.value = false
        }
    }
}
