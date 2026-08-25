package com.skillswap.ai.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.User
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.NotificationRepository
import com.skillswap.ai.data.repository.RequestRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val currentUser: User? = null,
    val allUsers: List<User> = emptyList(),
    val recentRequests: List<ExchangeRequest> = emptyList(),
    val unreadNotifications: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val requestRepository: RequestRepository,
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    val currentUserId: String get() = authRepository.currentUserId

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) kotlinx.coroutines.flow.flowOf(null) else userRepository.getUserFlow(uid)
            }.collect { user ->
                _uiState.update { it.copy(currentUser = user, isLoading = false) }
            }
        }

        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) {
                    kotlinx.coroutines.flow.flowOf(emptyList())
                } else {
                    userRepository.getUserFlow(uid).flatMapLatest { currentUser ->
                        val userDept = currentUser?.department ?: ""
                        userRepository.getAllUsersFlow().map { users ->
                            users.filter { it.uid != uid }
                        }
                    }
                }
            }.collect { otherUsers ->
                _uiState.update { it.copy(allUsers = otherUsers) }
            }
        }

        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) kotlinx.coroutines.flow.flowOf(emptyList()) else requestRepository.getReceivedRequests(uid)
            }.collect { requests ->
                _uiState.update { it.copy(recentRequests = requests.take(3)) }
            }
        }

        viewModelScope.launch {
            authRepository.currentUserIdFlow.collectLatest { uid ->
                if (uid.isNotEmpty()) {
                    val count = notificationRepository.getUnreadCount(uid)
                    _uiState.update { it.copy(unreadNotifications = count) }
                } else {
                    _uiState.update { it.copy(unreadNotifications = 0) }
                }
            }
        }
    }

    fun getAiRecommendedUsers(): List<User> {
        val current = _uiState.value.currentUser ?: return emptyList()
        return _uiState.value.allUsers
            .filter { user ->
                user.teachSkills.any { it in current.learnSkills } ||
                user.learnSkills.any { it in current.teachSkills }
            }
            .sortedByDescending { it.rating }
            .take(5)
    }

    fun getPopularSkills(): List<String> {
        val allSkills = _uiState.value.allUsers.flatMap { it.teachSkills }
        return allSkills.groupBy { it }.mapValues { it.value.size }
            .entries.sortedByDescending { it.value }.take(10).map { it.key }
    }

    fun refresh() {
        _uiState.update { it.copy(isLoading = true) }
        loadDashboard()
    }
}
