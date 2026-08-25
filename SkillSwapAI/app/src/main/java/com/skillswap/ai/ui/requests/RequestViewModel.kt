package com.skillswap.ai.ui.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.RequestStatus
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.CreditRepository
import com.skillswap.ai.data.repository.NotificationRepository
import com.skillswap.ai.data.repository.RequestRepository
import com.skillswap.ai.data.repository.MeetingRepository
import com.skillswap.ai.data.repository.SessionRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RequestsUiState(
    val sentRequests: List<ExchangeRequest> = emptyList(),
    val receivedRequests: List<ExchangeRequest> = emptyList(),
    val meetingRequests: List<com.skillswap.ai.data.model.MeetingRequest> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTab: Int = 0,        // 0 = Received, 1 = Sent
    val successMessage: String? = null,
    val error: String? = null,
    val learningSessionsForMeeting: List<com.skillswap.ai.data.model.LearningSession> = emptyList(),
    val ratedMeetingIds: Set<String> = emptySet()
)

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val requestRepository: RequestRepository,
    private val userRepository: UserRepository,
    private val notificationRepository: NotificationRepository,
    private val creditRepository: CreditRepository,
    private val sessionRepository: SessionRepository,
    private val meetingRepository: MeetingRepository,
    private val firestoreRepository: com.skillswap.ai.data.repository.FirestoreRepository,
    private val ratingRepository: com.skillswap.ai.data.repository.RatingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RequestsUiState())
    val uiState: StateFlow<RequestsUiState> = _uiState.asStateFlow()

    var conferenceJoined: Boolean = false
    private val completedInThisSession = mutableSetOf<String>()

    val currentUserId: String get() = authRepository.currentUserId

    init {
        loadRequests()
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private fun loadRequests() {
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) flowOf(emptyList()) else requestRepository.getSentRequests(uid)
            }.collect { sent ->
                _uiState.update { it.copy(sentRequests = sent.sortedByDescending { r -> r.createdAt }) }
            }
        }
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) flowOf(emptyList()) else requestRepository.getReceivedRequests(uid)
            }.collect { received ->
                _uiState.update { it.copy(receivedRequests = received.sortedByDescending { r -> r.createdAt }) }
            }
        }
        viewModelScope.launch {
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) flowOf(emptyList()) else meetingRepository.getMeetingRequestsForUser(uid)
            }.collect { meetings ->
                _uiState.update { it.copy(meetingRequests = meetings) }
                
                // After meetings update, also fetch ratings to determine ratedMeetingIds
                fetchRatedMeetings(meetings)
            }
        }
    }

    private fun fetchRatedMeetings(meetings: List<com.skillswap.ai.data.model.MeetingRequest>) {
        viewModelScope.launch {
            val uid = authRepository.currentUserId
            if (uid.isEmpty()) return@launch
            
            val ratedSet = mutableSetOf<String>()
            meetings.filter { it.meetingStatus == com.skillswap.ai.data.model.MeetingStatus.COMPLETED.name }.forEach { m ->
                if (ratingRepository.hasRatedMeeting(uid, m.meetingId)) {
                    ratedSet.add(m.meetingId)
                }
            }
            _uiState.update { it.copy(ratedMeetingIds = ratedSet) }
        }
    }

    fun refreshRatings() {
        fetchRatedMeetings(_uiState.value.meetingRequests)
    }

    fun fetchLearningSessionsForMeeting(meetingId: String) {
        viewModelScope.launch {
            val sessions = firestoreRepository.getLearningSessionsForMeeting(meetingId)
            _uiState.update { it.copy(learningSessionsForMeeting = sessions) }
        }
    }


    fun sendRequest(
        receiverId: String,
        receiverName: String,
        receiverProfilePic: String,
        teachSkill: String,
        learnSkill: String,
        message: String
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val uid = authRepository.currentUserId
            val senderResult = userRepository.getUser(uid)
            val sender = (senderResult as? com.skillswap.ai.data.repository.AuthResult.Success)?.data

            val request = ExchangeRequest(
                senderId = uid,
                senderName = sender?.name ?: "",
                senderProfilePic = sender?.profilePictureUrl ?: "",
                receiverId = receiverId,
                receiverName = receiverName,
                receiverProfilePic = receiverProfilePic,
                teachSkill = teachSkill,
                learnSkill = learnSkill,
                message = message,
                status = RequestStatus.PENDING.name,
                creditCost = 5
            )
            val result = requestRepository.sendRequest(request)
            if (result is com.skillswap.ai.data.repository.AuthResult.Success) {
                notificationRepository.sendRequestNotification(receiverId, sender?.name ?: "", sender?.profilePictureUrl ?: "", result.data)
                _uiState.update { it.copy(isLoading = false, successMessage = "Request sent!") }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Failed to send request") }
            }
        }
    }

    fun acceptRequest(request: ExchangeRequest) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Note: We don't deduct credits or create sessions here anymore.
            // That happens when the MeetingRequest is confirmed.
            
            requestRepository.updateRequestStatus(request.id, RequestStatus.ACCEPTED)
            notificationRepository.sendAcceptedNotification(
                request.senderId, authRepository.currentUserId, "Waiting for meeting schedule", request.id
            )
            _uiState.update { it.copy(isLoading = false, successMessage = "Skill Request accepted! Waiting for learner to schedule a meeting.") }
        }
    }

    fun rejectRequest(requestId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            requestRepository.updateRequestStatus(requestId, RequestStatus.REJECTED)
            _uiState.update { it.copy(isLoading = false, successMessage = "Request rejected.") }
        }
    }

    fun scheduleMeeting(
        request: ExchangeRequest,
        date: String,
        time: String,
        mode: String,
        locationOrLink: String,
        notes: String
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val uid = authRepository.currentUserId
            val senderResult = userRepository.getUser(uid)
            val sender = (senderResult as? com.skillswap.ai.data.repository.AuthResult.Success)?.data

            val meetingReq = com.skillswap.ai.data.model.MeetingRequest(
                requestId = request.id,
                learnerId = request.senderId,
                learnerName = request.senderName,
                learnerProfilePic = request.senderProfilePic,
                teacherId = request.receiverId,
                teacherName = request.receiverName,
                teacherProfilePic = request.receiverProfilePic,
                meetingDate = date,
                meetingTime = time,
                meetingMode = mode,
                meetingLocationOrLink = locationOrLink,
                notes = notes,
                meetingStatus = com.skillswap.ai.data.model.MeetingStatus.PENDING.name
            )

            val result = meetingRepository.createMeetingRequest(meetingReq)
            if (result is com.skillswap.ai.data.repository.AuthResult.Success) {
                // notificationRepository.sendMeetingRequestNotification(...)
                _uiState.update { it.copy(isLoading = false, successMessage = "Meeting request sent!") }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Failed to send meeting request") }
            }
        }
    }

    fun acceptMeeting(meeting: com.skillswap.ai.data.model.MeetingRequest, request: ExchangeRequest) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            
            // Note: Credits will be processed ONLY when the session is marked as completed
            // instead of taking an upfront escrow that causes failure for new users.

            val link = if (meeting.meetingMode == "Online") {
                if (meeting.meetingLocationOrLink.isBlank()) "https://meet.jit.si/exchange_${meeting.meetingId}" 
                else meeting.meetingLocationOrLink
            } else meeting.meetingLocationOrLink

            // Session 1: Sender teaches Receiver the teachSkill
            val session1 = com.skillswap.ai.data.model.Session(
                requestId = request.id,
                teacherId = request.senderId,
                teacherName = request.senderName,
                teacherProfilePic = request.senderProfilePic,
                learnerId = request.receiverId,
                learnerName = request.receiverName,
                learnerProfilePic = request.receiverProfilePic,
                skill = request.teachSkill,
                date = meeting.meetingDate,
                time = meeting.meetingTime,
                meetLink = link
            )
            sessionRepository.createSession(session1)

            // Session 2: Receiver teaches Sender the learnSkill
            val session2 = com.skillswap.ai.data.model.Session(
                requestId = request.id,
                teacherId = request.receiverId,
                teacherName = request.receiverName,
                teacherProfilePic = request.receiverProfilePic,
                learnerId = request.senderId,
                learnerName = request.senderName,
                learnerProfilePic = request.senderProfilePic,
                skill = request.learnSkill,
                date = meeting.meetingDate,
                time = meeting.meetingTime,
                meetLink = link
            )
            sessionRepository.createSession(session2)

            meetingRepository.updateMeetingStatus(meeting.meetingId, com.skillswap.ai.data.model.MeetingStatus.CONFIRMED)
            
            // Generate and save deterministic Jitsi room name
            val roomName = "exchange_${meeting.meetingId}"
            meetingRepository.updateJitsiRoomName(meeting.meetingId, roomName)

            notificationRepository.sendAcceptedNotification(
                request.senderId, authRepository.currentUserId, link, request.id
            )
            _uiState.update { it.copy(isLoading = false, successMessage = "Meeting Confirmed! Sessions Created. 🎉") }
        }
    }

    fun rejectMeeting(meetingId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            meetingRepository.updateMeetingStatus(meetingId, com.skillswap.ai.data.model.MeetingStatus.REJECTED)
            _uiState.update { it.copy(isLoading = false, successMessage = "Meeting rejected.") }
        }
    }

    fun cancelRequest(requestId: String) {
        viewModelScope.launch {
            requestRepository.updateRequestStatus(requestId, RequestStatus.CANCELLED)
        }
    }

    fun selectTab(tab: Int) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun generateAndSaveJitsiRoomName(meetingId: String) {
        viewModelScope.launch {
            val roomName = "exchange_$meetingId"
            meetingRepository.updateJitsiRoomName(meetingId, roomName)
        }
    }

    fun completeMeetingSession(exchangeId: String, meetingId: String) {
        if (completedInThisSession.contains(meetingId)) return
        completedInThisSession.add(meetingId)

        viewModelScope.launch {
            val meeting = _uiState.value.meetingRequests.find { it.meetingId == meetingId }
            if (meeting?.meetingStatus == com.skillswap.ai.data.model.MeetingStatus.COMPLETED.name) {
                return@launch // Already completed in backend
            }
            
            // Mark Meeting as COMPLETED
            meetingRepository.completeMeeting(meetingId)
            
            // Mark ExchangeRequest as COMPLETED
            requestRepository.updateRequestStatus(exchangeId, RequestStatus.COMPLETED)

            val request = _uiState.value.receivedRequests.find { it.id == exchangeId } 
                ?: _uiState.value.sentRequests.find { it.id == exchangeId }

            // Safely increment Portfolio sessions count for BOTH users
            if (request != null) {
                firestoreRepository.incrementSessionsCompleted(request.senderId)
                firestoreRepository.incrementSessionsCompleted(request.receiverId)
            } else {
                firestoreRepository.incrementSessionsCompleted(currentUserId)
            }
        }
    }
}
