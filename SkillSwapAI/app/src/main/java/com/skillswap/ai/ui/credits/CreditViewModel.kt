package com.skillswap.ai.ui.credits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.SkillCredit
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.CreditRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreditUiState(
    val balance: Int = 0,
    val history: List<SkillCredit> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class CreditViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val creditRepository: CreditRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreditUiState())
    val uiState: StateFlow<CreditUiState> = _uiState.asStateFlow()

    init {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            userRepository.getUserFlow(uid).collect { user ->
                _uiState.update { it.copy(balance = user?.skillCredits ?: 0) }
            }
        }
        viewModelScope.launch {
            creditRepository.getCreditHistory(uid).collect { history ->
                _uiState.update { it.copy(history = history) }
            }
        }
    }
}
