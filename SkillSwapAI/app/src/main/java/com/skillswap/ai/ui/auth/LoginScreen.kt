package com.skillswap.ai.ui.auth

import androidx.compose.animation.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.theme.*
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.skillswap.ai.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { idToken ->
                    viewModel.loginWithGoogle(idToken)
                }
            } catch (e: ApiException) {
                // Ignore or handle
            }
        }
    }

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
        if (uiState.isLoggedIn) onLoginSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(GradientStart, GradientMid),
                    start = Offset(0f, 0f),
                    end = Offset(400f, 800f)
                )
            )
    ) {
        // Top decoration
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-50).dp, y = (-80).dp)
                .background(Color.White.copy(alpha = 0.08f), shape = androidx.compose.foundation.shape.CircleShape)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 60.dp, y = 100.dp)
                .background(Color.White.copy(alpha = 0.05f), shape = androidx.compose.foundation.shape.CircleShape)
        )

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
            Spacer(Modifier.height(80.dp))

            // Logo & Title
            Text(
                "🔄", 
                fontSize = 64.sp,
                modifier = Modifier.graphicsLayer {
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "SkillSwap",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Learn. Teach. Grow.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(Modifier.height(40.dp))

            // Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Welcome Back!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Neutral10
                    )
                    Text(
                        "Sign in to continue your skill journey",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Neutral20
                    )

                    Spacer(Modifier.height(4.dp))

                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Blue40,
                            unfocusedBorderColor = Neutral90
                        )
                    )

                    // Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Blue40,
                            unfocusedBorderColor = Neutral90
                        )
                    )

                    // Forgot password
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Text(
                            "Forgot Password?",
                            style = MaterialTheme.typography.labelMedium,
                            color = Blue40,
                            modifier = Modifier.clickable { onNavigateToForgotPassword() }
                        )
                    }

                    // Error
                    AnimatedVisibility(visible = uiState.error != null) {
                        uiState.error?.let {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Red90),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    it,
                                    modifier = Modifier.padding(12.dp),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Red40
                                )
                            }
                        }
                    }

                    // Login Button
                    Button(
                        onClick = { viewModel.login(email, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        enabled = !uiState.isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue40
                        )
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                "Sign In",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    
                    Text("Or", style = MaterialTheme.typography.bodySmall, color = Neutral20, modifier = Modifier.align(Alignment.CenterHorizontally))
                    
                    Spacer(Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = {
                            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken("384474104749-97vb0rs0vloah6e5uj0mk5jnn3437ogm.apps.googleusercontent.com")
                                .requestEmail()
                                .build()
                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            // Sign out first to ensure account picker shows if they want to switch
                            googleSignInClient.signOut().addOnCompleteListener {
                                launcher.launch(googleSignInClient.signInIntent)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            "Sign in with Google",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Neutral20
                        )
                    }

                    Divider(color = Neutral90)

                    // Sign up link
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Don't have an account? ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral20
                        )
                        Text(
                            "Sign Up",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Blue40,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onNavigateToSignup() }
                        )
                    }
                }
            }
        }
        }
    }
}
