package com.skillswap.ai.ui.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skillswap.ai.ui.components.ProfilePhoto
import com.skillswap.ai.ui.requests.RequestViewModel
import com.skillswap.ai.ui.theme.Blue40
import com.skillswap.ai.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionCompletedScreen(
    exchangeId: String,
    meetingId: String,
    onNavigateToRating: (String, String, String, String, String) -> Unit,
    onNavigateToRequests: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    val request = uiState.receivedRequests.find { it.id == exchangeId }
        ?: uiState.sentRequests.find { it.id == exchangeId }
    
    if (request == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val isSender = request.senderId == viewModel.currentUserId
    val otherName = if (isSender) request.receiverName else request.senderName
    val otherPic = if (isSender) request.receiverProfilePic else request.senderProfilePic
    val otherId = if (isSender) request.receiverId else request.senderId

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(80.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Session Completed",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Your skill exchange session with",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            ProfilePhoto(url = otherPic, contentDescription = otherName, size = 80.dp)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = otherName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "has been completed successfully.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = request.teachSkill,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Blue40
                    )
                    Text(
                        text = "↔",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = request.learnSkill,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Purple40
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Button(
                onClick = { 
                    // Navigate to Rating Screen with full params
                    val skillRated = if (isSender) request.teachSkill else request.learnSkill
                    onNavigateToRating(meetingId, exchangeId, otherId, otherName, skillRated) 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Rate Exchange", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(
                onClick = onNavigateToRequests
            ) {
                Text("Skip for now", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
        }
    }
}
