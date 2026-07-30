package com.skillswap.ai.ui.sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.Session
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.CreditRepository
import com.skillswap.ai.data.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SessionUiState(
    val teachingSessions: List<Session> = emptyList(),
    val learningSessions: List<Session> = emptyList(),
    val selectedTab: Int = 0,
    val isLoading: Boolean = false,
    val successMessage: String? = null
)

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val creditRepository: CreditRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    val currentUserId: String get() = authRepository.currentUserId

    init {
        loadSessions()
    }

    private fun loadSessions() {
        val uid = authRepository.currentUserId
        viewModelScope.launch {
            sessionRepository.getTeacherSessions(uid).collect { sessions ->
                _uiState.update { it.copy(teachingSessions = sessions) }
            }
        }
        viewModelScope.launch {
            sessionRepository.getLearnerSessions(uid).collect { sessions ->
                _uiState.update { it.copy(learningSessions = sessions) }
            }
        }
    }

    fun markComplete(session: Session) {
        if (session.status == com.skillswap.ai.data.model.SessionStatus.COMPLETED.name) return
        viewModelScope.launch {
            sessionRepository.markSessionComplete(session.id)
            // Refund the 5 credits escrow to both parties
            creditRepository.earnCredits(
                userId = session.teacherId,
                amount = 5,
                description = "Escrow Refund: Taught ${session.skill}",
                sessionId = session.id
            )
            creditRepository.earnCredits(
                userId = session.learnerId,
                amount = 5,
                description = "Escrow Refund: Learned ${session.skill}",
                sessionId = session.id
            )
            _uiState.update { it.copy(successMessage = "Meeting Ended! Escrow credits refunded. 🎉") }
        }
    }

    fun updateNotes(sessionId: String, notes: String) {
        viewModelScope.launch {
            sessionRepository.updateNotes(sessionId, notes)
        }
    }

    fun scheduleSession(sessionId: String, date: String, time: String) {
        viewModelScope.launch {
            val result = sessionRepository.scheduleSession(sessionId, date, time)
            if (result is com.skillswap.ai.data.repository.AuthResult.Success) {
                _uiState.update { it.copy(successMessage = "Meeting Scheduled! 📅") }
            }
        }
    }

    fun selectTab(tab: Int) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}
