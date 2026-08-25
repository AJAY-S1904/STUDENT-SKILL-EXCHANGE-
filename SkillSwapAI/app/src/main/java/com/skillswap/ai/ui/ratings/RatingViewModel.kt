package com.skillswap.ai.ui.ratings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.Rating
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.AuthResult
import com.skillswap.ai.data.repository.RatingRepository
import com.skillswap.ai.data.repository.SessionRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RatingUiState(
    val stars: Float = 0f,
    val feedback: String = "",
    val sessionSkill: String = "",
    val ratedUserName: String = "",
    val ratedUserId: String = "",
    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false,
    val alreadyRated: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val ratingRepository: RatingRepository,
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RatingUiState())
    val uiState: StateFlow<RatingUiState> = _uiState.asStateFlow()

    fun loadSessionDetails(sessionId: String) {
        // Legacy
    }

    fun checkIfAlreadyRated(meetingId: String) {
        viewModelScope.launch {
            val uid = authRepository.currentUserId
            val hasRated = ratingRepository.hasRatedMeeting(uid, meetingId)
            if (hasRated) {
                _uiState.update { it.copy(alreadyRated = true) }
            }
        }
    }

    fun setStars(stars: Float) {
        _uiState.update { it.copy(stars = stars) }
    }

    fun setFeedback(feedback: String) {
        _uiState.update { it.copy(feedback = feedback) }
    }

    fun submitRating(meetingId: String, exchangeId: String, ratedUserId: String, ratedUserName: String, skill: String) {
        val stars = _uiState.value.stars
        if (stars == 0f) {
            _uiState.update { it.copy(error = "Please select a star rating") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val uid = authRepository.currentUserId
            
            // Final check
            if (ratingRepository.hasRatedMeeting(uid, meetingId)) {
                _uiState.update { it.copy(isLoading = false, alreadyRated = true) }
                return@launch
            }
            
            val rater = userRepository.getUser(uid)
            val raterData = (rater as? AuthResult.Success)?.data

            val rating = Rating(
                meetingId = meetingId,
                exchangeId = exchangeId,
                raterId = uid,
                raterName = raterData?.name ?: "",
                raterProfilePic = raterData?.profilePictureUrl ?: "",
                ratedUserId = ratedUserId,
                stars = stars,
                feedback = _uiState.value.feedback,
                skill = skill
            )
            ratingRepository.submitRating(rating)
            _uiState.update { it.copy(isLoading = false, isSubmitted = true) }
        }
    }
}
