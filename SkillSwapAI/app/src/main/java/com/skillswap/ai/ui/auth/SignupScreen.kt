package com.skillswap.ai.ui.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.animation.core.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit,
    onSignupSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }

    // Animations
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }
    
    val infiniteTransition = rememberInfiniteTransition()
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "pulse"
    )

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) onSignupSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Purple30, GradientMid, Blue40),
                    start = Offset(0f, 0f),
                    end = Offset(400f, 900f)
                )
            )
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { 100 },
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(800)),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(Modifier.height(60.dp))

            Text(
                "✨", 
                fontSize = 52.sp,
                modifier = Modifier.graphicsLayer {
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Join SkillSwap",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Create your free account",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        "Student Details",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Neutral10
                    )

                    // Full Name
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Full Name") },
                        leadingIcon = { Icon(Icons.Filled.Person, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                    )

                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        leadingIcon = { Icon(Icons.Filled.Email, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                    )

                    // College
                    OutlinedTextField(
                        value = college,
                        onValueChange = { college = it },
                        label = { Text("College / University") },
                        leadingIcon = { Icon(Icons.Filled.School, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                    )

                    // Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Filled.Lock, null) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                    )

                    // Confirm Password
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm Password") },
                        leadingIcon = { Icon(Icons.Filled.LockOpen, null) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Purple40)
                    )

                    // Errors
                    AnimatedVisibility(visible = localError != null || uiState.error != null) {
                        val msg = localError ?: uiState.error ?: ""
                        Card(colors = CardDefaults.cardColors(Red90), shape = RoundedCornerShape(10.dp)) {
                            Text(msg, Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall, color = Red40)
                        }
                    }

                    // Sign Up Button
                    Button(
                        onClick = {
                            localError = null
                            if (password != confirmPassword) {
                                localError = "Passwords do not match"
                                return@Button
                            }
                            if (password.length < 6) {
                                localError = "Password must be at least 6 characters"
                                return@Button
                            }
                            viewModel.signUp(email, password, name, college)
                        },
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        enabled = !uiState.isLoading,
                        colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                        } else {
                            Text("Create Account", fontWeight = FontWeight.Bold)
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text("Already have an account? ", style = MaterialTheme.typography.bodyMedium, color = Neutral20)
                        Text(
                            "Sign In",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Purple40,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onNavigateToLogin() }
                        )
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
        }
    }
}
