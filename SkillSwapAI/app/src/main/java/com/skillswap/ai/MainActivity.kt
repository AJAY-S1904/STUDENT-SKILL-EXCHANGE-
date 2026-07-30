package com.skillswap.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.skillswap.ai.ui.auth.AuthViewModel
import com.skillswap.ai.ui.navigation.AppNavigation
import com.skillswap.ai.ui.navigation.BottomNavBar
import com.skillswap.ai.ui.navigation.Routes
import com.skillswap.ai.ui.theme.SkillSwapAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillSwapAITheme {
                SkillSwapAppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillSwapAppContent() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val startDestination = if (authViewModel.isLoggedIn) Routes.DASHBOARD else Routes.LOGIN

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        AppNavigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding()
                .imePadding(),
            navController = navController,
            startDestination = startDestination,
            authViewModel = authViewModel
        )
    }
}
