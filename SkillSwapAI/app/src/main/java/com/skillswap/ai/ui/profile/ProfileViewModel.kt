package com.skillswap.ai.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.User
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) kotlinx.coroutines.flow.flowOf(null) else userRepository.getUserFlow(uid)
            }.collect { user ->
                _uiState.update { it.copy(user = user) }
            }
        }
    }

    fun updateProfile(
        name: String, college: String, department: String, year: String, bio: String
    ) {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val updates = mapOf(
                "name" to name,
                "college" to college,
                "department" to department,
                "year" to year,
                "bio" to bio
            )
            userRepository.updateUser(uid, updates)
            _uiState.update { it.copy(isLoading = false, successMessage = "Profile updated!") }
        }
    }

    fun uploadProfilePicture(uri: Uri) {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.uploadProfilePicture(uid, uri)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun setAvatarUrl(url: String) {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.updateUser(uid, mapOf("profilePictureUrl" to url))
            _uiState.update { it.copy(isLoading = false, successMessage = "Avatar updated!") }
        }
    }

    fun updateAvailability(days: List<String>) {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            userRepository.updateUser(uid, mapOf("availability" to days))
        }
    }

    fun updateExperience(level: String) {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            userRepository.updateUser(uid, mapOf("experienceLevel" to level))
        }
    }
}
