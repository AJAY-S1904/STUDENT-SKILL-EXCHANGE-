package com.skillswap.ai.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.AppNotification
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NotificationUiState(
    val notifications: List<AppNotification> = emptyList(),
    val unreadCount: Int = 0,
    val isLoading: Boolean = false
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) kotlinx.coroutines.flow.flowOf(emptyList()) else notificationRepository.getUserNotifications(uid)
            }.collect { notifications ->
                _uiState.update {
                    it.copy(
                        notifications = notifications,
                        unreadCount = notifications.count { n -> !n.getIsActuallyRead() }
                    )
                }
            }
        }
    }

    fun markAsRead(notificationId: String) {
        viewModelScope.launch {
            notificationRepository.markAsRead(notificationId)
        }
    }

    fun markAllAsRead() {
        viewModelScope.launch {
            notificationRepository.markAllAsRead(authRepository.currentUserId)
        }
    }
}
