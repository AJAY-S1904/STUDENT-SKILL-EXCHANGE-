package com.skillswap.ai.ui.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.skillswap.ai.ui.auth.*
import com.skillswap.ai.ui.credits.CreditsScreen
import com.skillswap.ai.ui.dashboard.DashboardScreen
import com.skillswap.ai.ui.matching.AiMatchingScreen
import com.skillswap.ai.ui.notifications.NotificationsScreen
import com.skillswap.ai.ui.profile.ProfileScreen
import com.skillswap.ai.ui.ratings.RatingScreen
import com.skillswap.ai.ui.requests.RequestsScreen
import com.skillswap.ai.ui.search.SearchScreen
import com.skillswap.ai.ui.sessions.SessionsScreen
import com.skillswap.ai.ui.skills.SkillManagementScreen

// ── Routes ───────────────────────────────────────────────────────────────────
object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val FORGOT_PASSWORD = "forgot_password"
    const val DASHBOARD = "dashboard"
    const val SEARCH = "search"
    const val REQUESTS = "requests"
    const val NOTIFICATIONS = "notifications"
    const val PROFILE = "profile"
    const val STUDENT_PROFILE = "student_profile/{studentId}"
    const val AI_MATCHING = "ai_matching"
    const val SKILL_MANAGEMENT = "skill_management"
    const val SESSIONS = "sessions"
    const val CREDITS = "credits"
    const val RATING = "rating/{sessionId}/{ratedUserId}/{ratedUserName}/{skill}"

    fun ratingRoute(sessionId: String, ratedUserId: String, ratedUserName: String, skill: String): String {
        return "rating/$sessionId/$ratedUserId/$ratedUserName/$skill"
    }
}

// ── Bottom Nav Items ──────────────────────────────────────────────────────────
data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Routes.DASHBOARD, "Home", Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem(Routes.SEARCH, "Search", Icons.Filled.Search, Icons.Outlined.Search),
    BottomNavItem(Routes.REQUESTS, "Requests", Icons.Filled.SwapHoriz, Icons.Outlined.SwapHoriz),
    BottomNavItem(Routes.NOTIFICATIONS, "Alerts", Icons.Filled.Notifications, Icons.Outlined.NotificationsNone),
    BottomNavItem(Routes.PROFILE, "Profile", Icons.Filled.Person, Icons.Outlined.Person)
)

val bottomNavRoutes = bottomNavItems.map { it.route }

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn() + slideInHorizontally { it / 4 } },
        exitTransition = { fadeOut() + slideOutHorizontally { -it / 4 } },
        popEnterTransition = { fadeIn() + slideInHorizontally { -it / 4 } },
        popExitTransition = { fadeOut() + slideOutHorizontally { it / 4 } }
    ) {
        // ── Auth ─────────────────────────────────────────────────────────────
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToSignup = { navController.navigate(Routes.SIGNUP) },
                onNavigateToForgotPassword = { navController.navigate(Routes.FORGOT_PASSWORD) },
                onLoginSuccess = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.SIGNUP) {
            SignupScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onSignupSuccess = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // ── Main App ──────────────────────────────────────────────────────────
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onNavigateToSearch = { navController.navigate(Routes.SEARCH) },
                onNavigateToProfile = { navController.navigate(Routes.PROFILE) },
                onNavigateToMatching = { navController.navigate(Routes.AI_MATCHING) },
                onNavigateToStudentProfile = { studentId -> navController.navigate("student_profile/$studentId") },
                onNavigateToNotifications = { navController.navigate(Routes.NOTIFICATIONS) },
                onNavigateToSessions = { navController.navigate(Routes.SESSIONS) }
            )
        }

        composable(Routes.SEARCH) {
            SearchScreen(onNavigateToStudentProfile = { studentId -> navController.navigate("student_profile/$studentId") })
        }

        composable(Routes.REQUESTS) {
            RequestsScreen(
                onNavigateToSessions = { navController.navigate(Routes.SESSIONS) }
            )
        }

        composable(Routes.NOTIFICATIONS) {
            NotificationsScreen(
                onNavigateToRequests = {
                    navController.navigate(Routes.REQUESTS) {
                        popUpTo(Routes.DASHBOARD) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToSessions = {
                    navController.navigate(Routes.SESSIONS) {
                        popUpTo(Routes.DASHBOARD) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToSkills = { navController.navigate(Routes.SKILL_MANAGEMENT) }
            )
        }

        composable(
            route = Routes.STUDENT_PROFILE,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) {
            com.skillswap.ai.ui.profile.StudentProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.AI_MATCHING) {
            AiMatchingScreen(
                onSendRequest = { receiverUid ->
                    // Navigate to send request dialog
                    navController.navigate(Routes.REQUESTS)
                }
            )
        }

        composable(Routes.SKILL_MANAGEMENT) {
            SkillManagementScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SESSIONS) {
            SessionsScreen(
                onNavigateToRating = { session ->
                    val uid = authViewModel.currentUserId
                    val ratedUserId = if (uid == session.teacherId) session.learnerId else session.teacherId
                    val ratedUserName = if (uid == session.teacherId) session.learnerName else session.teacherName
                    navController.navigate(Routes.ratingRoute(session.id, ratedUserId, ratedUserName, session.skill))
                }
            )
        }

        composable(Routes.CREDITS) {
            CreditsScreen()
        }

        composable(
            route = Routes.RATING,
            arguments = listOf(
                navArgument("sessionId") { type = NavType.StringType },
                navArgument("ratedUserId") { type = NavType.StringType },
                navArgument("ratedUserName") { type = NavType.StringType },
                navArgument("skill") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RatingScreen(
                sessionId = backStackEntry.arguments?.getString("sessionId") ?: "",
                ratedUserId = backStackEntry.arguments?.getString("ratedUserId") ?: "",
                ratedUserName = backStackEntry.arguments?.getString("ratedUserName") ?: "",
                skill = backStackEntry.arguments?.getString("skill") ?: "",
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute in bottomNavRoutes) {
        NavigationBar(
            tonalElevation = 8.dp
        ) {
            bottomNavItems.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(item.label, style = MaterialTheme.typography.labelSmall) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(Routes.DASHBOARD) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }
}
