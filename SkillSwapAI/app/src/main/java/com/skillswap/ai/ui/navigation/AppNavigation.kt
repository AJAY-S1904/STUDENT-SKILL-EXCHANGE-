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
import com.skillswap.ai.ui.tools.ToolsScreen

// ── Routes ───────────────────────────────────────────────────────────────────
object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val FORGOT_PASSWORD = "forgot_password"
    const val DASHBOARD = "dashboard"
    const val SEARCH = "search"
    const val REQUESTS = "requests"
    const val EXCHANGE_DETAIL = "exchange_detail/{exchangeId}"
    const val NOTIFICATIONS = "notifications"
    const val PROFILE = "profile"
    const val STUDENT_PROFILE = "student_profile/{studentId}"
    const val AI_MATCHING = "ai_matching"
    const val SKILL_MANAGEMENT = "skill_management"
    const val SESSIONS = "sessions"
    const val CREDITS = "credits"
    const val RATING = "rating/{meetingId}/{exchangeId}/{ratedUserId}/{ratedUserName}/{skill}"
    const val SESSION_COMPLETED = "session_completed/{exchangeId}/{meetingId}"
    
    const val TOOLS = "tools"
    const val AI_ANALYSIS_HISTORY = "ai_analysis_history"
    const val NEW_AI_ANALYSIS = "new_ai_analysis"
    const val SKILL_GAP = "skill_gap/{analysisId}"
    const val LEARNING_ROADMAP_HISTORY = "learning_roadmap_history"
    const val LEARNING_ROADMAP = "learning_roadmap/{analysisId}"
    const val MCQ_TEST = "mcq_test?skill={skill}"
    const val SKILL_PORTFOLIO = "skill_portfolio"
    const val MULTI_SESSION_MGMT = "multi_session_mgmt/{meetingId}"

    fun mcqTestRoute(skill: String? = null): String {
        return if (skill != null) "mcq_test?skill=$skill" else "mcq_test"
    }
    
    fun exchangeDetailRoute(exchangeId: String) = "exchange_detail/$exchangeId"

    fun ratingRoute(meetingId: String, exchangeId: String, ratedUserId: String, ratedUserName: String, skill: String): String {
        return "rating/$meetingId/$exchangeId/$ratedUserId/$ratedUserName/$skill"
    }
    
    fun sessionCompletedRoute(exchangeId: String, meetingId: String): String {
        return "session_completed/$exchangeId/$meetingId"
    }

    fun multiSessionMgmtRoute(meetingId: String): String {
        return "multi_session_mgmt/$meetingId"
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
    authViewModel: AuthViewModel = hiltViewModel(),
    aiFeaturesViewModel: com.skillswap.ai.ui.ai.AiFeaturesViewModel = hiltViewModel()
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
                onNavigateToSessions = { navController.navigate(Routes.SESSIONS) },
                onNavigateToTools = { navController.navigate(Routes.TOOLS) }
            )
        }

        composable(Routes.SEARCH) {
            SearchScreen(onNavigateToStudentProfile = { studentId -> navController.navigate("student_profile/$studentId") })
        }

        composable(Routes.REQUESTS) {
            RequestsScreen(
                onNavigateToSearch = { navController.navigate(Routes.SEARCH) },
                onNavigateToDetail = { exchangeId -> navController.navigate(Routes.exchangeDetailRoute(exchangeId)) },
                onNavigateToSessions = { navController.navigate(Routes.SESSIONS) },
                onNavigateToMultiSession = { meetingId -> navController.navigate(Routes.multiSessionMgmtRoute(meetingId)) },
                onNavigateToRating = { mId, eId, rUserId, rUserName, skill ->
                    navController.navigate(Routes.ratingRoute(mId, eId, rUserId, rUserName, skill))
                }
            )
        }

        composable(Routes.EXCHANGE_DETAIL) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: return@composable
            com.skillswap.ai.ui.requests.ExchangeDetailScreen(
                exchangeId = exchangeId,
                onBack = { navController.popBackStack() },
                onNavigateToMultiSession = { meetingId -> navController.navigate(Routes.multiSessionMgmtRoute(meetingId)) },
                onNavigateToSessionCompleted = { exId, mId ->
                    navController.navigate(Routes.sessionCompletedRoute(exId, mId))
                }
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
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMcqTest = { skill -> navController.navigate(Routes.mcqTestRoute(skill)) }
            )
        }

        composable(Routes.SESSIONS) {
            SessionsScreen(
                onNavigateToRating = { session ->
                    val uid = authViewModel.currentUserId
                    val ratedUserId = if (uid == session.teacherId) session.learnerId else session.teacherId
                    val ratedUserName = if (uid == session.teacherId) session.learnerName else session.teacherName
                    val meetingId = session.id // Or fetch meetingId
                    val exchangeId = session.requestId
                    navController.navigate(Routes.ratingRoute(meetingId, exchangeId, ratedUserId, ratedUserName, session.skill))
                }
            )
        }

        composable(Routes.CREDITS) {
            CreditsScreen()
        }

        composable(
            route = Routes.RATING,
            arguments = listOf(
                navArgument("meetingId") { type = NavType.StringType },
                navArgument("exchangeId") { type = NavType.StringType },
                navArgument("ratedUserId") { type = NavType.StringType },
                navArgument("ratedUserName") { type = NavType.StringType },
                navArgument("skill") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RatingScreen(
                meetingId = backStackEntry.arguments?.getString("meetingId") ?: "",
                exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: "",
                ratedUserId = backStackEntry.arguments?.getString("ratedUserId") ?: "",
                ratedUserName = backStackEntry.arguments?.getString("ratedUserName") ?: "",
                skill = backStackEntry.arguments?.getString("skill") ?: "",
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.SESSION_COMPLETED,
            arguments = listOf(
                navArgument("exchangeId") { type = NavType.StringType },
                navArgument("meetingId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: return@composable
            val meetingId = backStackEntry.arguments?.getString("meetingId") ?: return@composable
            com.skillswap.ai.ui.sessions.SessionCompletedScreen(
                exchangeId = exchangeId,
                meetingId = meetingId,
                onNavigateToRating = { mId, eId, rUserId, rUserName, skill ->
                    navController.navigate(Routes.ratingRoute(mId, eId, rUserId, rUserName, skill)) {
                        popUpTo(Routes.SESSION_COMPLETED) { inclusive = true }
                    }
                },
                onNavigateToRequests = {
                    navController.navigate(Routes.REQUESTS) {
                        popUpTo(Routes.SESSION_COMPLETED) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.TOOLS) {
            ToolsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCareerAnalysis = { navController.navigate(Routes.AI_ANALYSIS_HISTORY) },
                onNavigateToLearningRoadmap = { navController.navigate(Routes.LEARNING_ROADMAP_HISTORY) },
                onNavigateToSkillPortfolio = { navController.navigate(Routes.SKILL_PORTFOLIO) }
            )
        }

        composable(Routes.AI_ANALYSIS_HISTORY) {
            com.skillswap.ai.ui.ai.AiCareerAnalysisHistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToNewAnalysis = { navController.navigate(Routes.NEW_AI_ANALYSIS) },
                onNavigateToAnalysis = { analysisId -> navController.navigate("skill_gap/$analysisId") },
                onNavigateToRoadmap = { analysisId -> 
                    navController.navigate("learning_roadmap/$analysisId") 
                },
                viewModel = aiFeaturesViewModel
            )
        }
        
        composable(Routes.NEW_AI_ANALYSIS) {
            com.skillswap.ai.ui.ai.NewAnalysisScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHistory = {
                    navController.navigate(Routes.AI_ANALYSIS_HISTORY) {
                        popUpTo(Routes.AI_ANALYSIS_HISTORY) { inclusive = true }
                    }
                },
                viewModel = aiFeaturesViewModel
            )
        }

        composable(
            route = Routes.SKILL_GAP,
            arguments = listOf(navArgument("analysisId") { type = NavType.StringType })
        ) { backStackEntry ->
            val analysisId = backStackEntry.arguments?.getString("analysisId") ?: ""
            com.skillswap.ai.ui.ai.SkillGapScreen(
                analysisId = analysisId,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRoadmap = { id ->
                    navController.navigate("learning_roadmap/$id")
                },
                viewModel = aiFeaturesViewModel
            )
        }

        composable(Routes.LEARNING_ROADMAP_HISTORY) {
            com.skillswap.ai.ui.ai.LearningRoadmapHistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRoadmap = { id -> navController.navigate("learning_roadmap/$id") },
                viewModel = aiFeaturesViewModel
            )
        }

        composable(
            route = Routes.LEARNING_ROADMAP,
            arguments = listOf(navArgument("analysisId") { type = NavType.StringType })
        ) { backStackEntry ->
            val analysisId = backStackEntry.arguments?.getString("analysisId") ?: ""
            com.skillswap.ai.ui.ai.LearningRoadmapScreen(
                analysisId = analysisId,
                onNavigateBack = { navController.popBackStack() },
                viewModel = aiFeaturesViewModel
            )
        }

        composable(
            route = Routes.MCQ_TEST,
            arguments = listOf(navArgument("skill") { 
                type = NavType.StringType
                nullable = true
                defaultValue = null
            })
        ) { backStackEntry ->
            val skill = backStackEntry.arguments?.getString("skill")
            com.skillswap.ai.ui.ai.McqTestScreen(
                initialSkill = skill,
                onNavigateBack = { navController.popBackStack() },
                viewModel = aiFeaturesViewModel
            )
        }

        composable(Routes.SKILL_PORTFOLIO) {
            com.skillswap.ai.ui.portfolio.SkillPortfolioScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.MULTI_SESSION_MGMT,
            arguments = listOf(navArgument("meetingId") { type = NavType.StringType })
        ) { backStackEntry ->
            com.skillswap.ai.ui.sessions.MultiSessionManagementScreen(
                meetingId = backStackEntry.arguments?.getString("meetingId") ?: "",
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
