package com.skillswap.ai.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Blue40, Purple40),
                    start = Offset(0f, 0f),
                    end = Offset(400f, 600f)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))

            // Back
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            Spacer(Modifier.height(32.dp))
            Text("🔐", fontSize = 60.sp)
            Spacer(Modifier.height(16.dp))
            Text("Forgot Password?", style = MaterialTheme.typography.headlineSmall, color = Color.White, fontWeight = FontWeight.Bold)
            Text("We'll send a reset link to your email", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center)

            Spacer(Modifier.height(40.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        leadingIcon = { Icon(Icons.Filled.Email, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Blue40)
                    )

                    AnimatedVisibility(uiState.error != null) {
                        Card(colors = CardDefaults.cardColors(Red90), shape = RoundedCornerShape(10.dp)) {
                            Text(uiState.error ?: "", Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall, color = Red40)
                        }
                    }

                    AnimatedVisibility(uiState.successMessage != null) {
                        Card(colors = CardDefaults.cardColors(Color(0xFFE8F5E9)), shape = RoundedCornerShape(10.dp)) {
                            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.CheckCircle, null, tint = AcceptedColor, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Text(uiState.successMessage ?: "", style = MaterialTheme.typography.bodySmall, color = AcceptedColor)
                            }
                        }
                    }

                    Button(
                        onClick = { viewModel.forgotPassword(email) },
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        enabled = !uiState.isLoading,
                        colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                        } else {
                            Text("Send Reset Link", fontWeight = FontWeight.Bold)
                        }
                    }

                    TextButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
                        Text("Back to Login", color = Blue40)
                    }
                }
            }
        }
    }
}
