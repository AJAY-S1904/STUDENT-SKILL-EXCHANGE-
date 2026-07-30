package com.skillswap.ai.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.User
import com.skillswap.ai.data.repository.AuthResult
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StudentProfileUiState(
    val student: User? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class StudentProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(StudentProfileUiState())
    val uiState: StateFlow<StudentProfileUiState> = _uiState.asStateFlow()

    private val studentId: String = checkNotNull(savedStateHandle["studentId"])

    init {
        loadStudent()
    }

    private fun loadStudent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = userRepository.getUser(studentId)
            if (result is AuthResult.Success) {
                _uiState.update { it.copy(student = result.data, isLoading = false) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Failed to load student profile") }
            }
        }
    }
}
