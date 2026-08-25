package com.skillswap.ai.ui.ratings

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.components.GradientBanner
import com.skillswap.ai.ui.components.StarRatingBar
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen(
    meetingId: String,
    exchangeId: String,
    ratedUserId: String,
    ratedUserName: String,
    skill: String,
    onNavigateBack: () -> Unit,
    viewModel: RatingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(meetingId) {
        viewModel.checkIfAlreadyRated(meetingId)
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        GradientBanner {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                }
                Column {
                    Text("Rate Session", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Share your experience", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.8f))
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (uiState.isSubmitted) {
                // Success State
                Spacer(Modifier.weight(1f))
                Text("🎉", fontSize = 80.sp)
                Text("Rating Submitted!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("Thank you for your feedback!", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface.copy(0.6f), textAlign = TextAlign.Center)
                Spacer(Modifier.height(20.dp))
                Button(onClick = onNavigateBack, shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                    Text("Go Back")
                }
                Spacer(Modifier.weight(1f))
            } else if (uiState.alreadyRated) {
                // Already Rated State
                Spacer(Modifier.weight(1f))
                Text("✅", fontSize = 80.sp)
                Text("Already Rated", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("You have already rated this exchange.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface.copy(0.6f), textAlign = TextAlign.Center)
                Spacer(Modifier.height(20.dp))
                Button(onClick = onNavigateBack, shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                    Text("Go Back")
                }
                Spacer(Modifier.weight(1f))
            } else {
                Spacer(Modifier.height(8.dp))

                // Who you're rating
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(3.dp)) {
                    Column(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .background(
                                    brush = androidx.compose.ui.graphics.Brush.radialGradient(listOf(Blue90, Purple90)),
                                    shape = androidx.compose.foundation.shape.CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(ratedUserName.firstOrNull()?.toString() ?: "?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Blue30)
                        }
                        Text("How was your session with", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(0.6f))
                        Text(ratedUserName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Surface(shape = RoundedCornerShape(50), color = Blue90) {
                            Text("🎯 $skill", modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium, color = Blue30)
                        }
                    }
                }

                // Stars
                Card(shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Your Rating", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        StarRatingBar(
                            rating = uiState.stars,
                            onRatingChanged = { viewModel.setStars(it) },
                            starSize = 48
                        )
                        Text(
                            when (uiState.stars.toInt()) {
                                1 -> "😞 Poor"
                                2 -> "😐 Fair"
                                3 -> "🙂 Good"
                                4 -> "😊 Great"
                                5 -> "🌟 Excellent!"
                                else -> "Tap to rate"
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = if (uiState.stars > 0) AcceptedColor else Neutral20
                        )
                    }
                }

                // Feedback
                OutlinedTextField(
                    value = uiState.feedback,
                    onValueChange = viewModel::setFeedback,
                    label = { Text("Write your feedback (optional)") },
                    placeholder = { Text("What did you like about this session?") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    minLines = 4,
                    maxLines = 6,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Blue40)
                )

                AnimatedVisibility(uiState.error != null) {
                    Card(colors = CardDefaults.cardColors(Red90), shape = RoundedCornerShape(10.dp)) {
                        Text(uiState.error ?: "", Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall, color = Red40)
                    }
                }

                Button(
                    onClick = { viewModel.submitRating(meetingId, exchangeId, ratedUserId, ratedUserName, skill) },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    enabled = !uiState.isLoading && uiState.stars > 0f,
                    colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                    } else {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text("⭐", fontSize = 18.sp)
                            Text("Submit Rating", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
