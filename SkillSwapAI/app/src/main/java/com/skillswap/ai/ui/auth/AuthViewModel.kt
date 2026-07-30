package com.skillswap.ai.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.skillswap.ai.data.model.User
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.AuthResult
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    val isLoggedIn: Boolean get() = authRepository.isLoggedIn
    val currentUserId: String get() = authRepository.currentUserId

    fun signUp(email: String, password: String, name: String, college: String) {
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Please fill all required fields")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = authRepository.signUp(email, password, name)) {
                is AuthResult.Success -> {
                    val user = User(
                        uid = result.data.uid,
                        email = email,
                        name = name,
                        studentId = "STU-${(100000..999999).random()}",
                        college = college,
                        skillCredits = 10
                    )
                    userRepository.createUser(user)
                    _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Please enter email and password")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = authRepository.login(email, password)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = authRepository.loginWithGoogle(idToken)) {
                is AuthResult.Success -> {
                    // Check if they need a user record in RTDB
                    try {
                        val existingUser = userRepository.getUser(result.data.uid)
                        if (existingUser is AuthResult.Error || (existingUser is AuthResult.Success && existingUser.data == null)) {
                            // Create a new user record if they don't exist
                            val user = User(
                                uid = result.data.uid,
                                email = result.data.email ?: "",
                                name = result.data.displayName ?: "Google User",
                                studentId = "STU-${(100000..999999).random()}",
                                college = "Unknown",
                                skillCredits = 10,
                                profilePictureUrl = result.data.photoUrl?.toString() ?: ""
                            )
                            userRepository.createUser(user)
                        }
                    } catch (e: Exception) {
                        // ignore error
                    }
                    _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun forgotPassword(email: String) {
        if (email.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Please enter your email")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = authRepository.forgotPassword(email)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Reset email sent! Check your inbox."
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _uiState.value = AuthUiState(isLoggedIn = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null, successMessage = null)
    }
}
