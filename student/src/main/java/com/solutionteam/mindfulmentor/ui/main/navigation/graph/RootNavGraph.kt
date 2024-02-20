package com.solutionteam.mindfulmentor.ui.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.chatBotScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.main.navigation.individualMeetingNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.loginNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.mainNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.mentorNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.notificationNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.onSeeAllScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.onboardingScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.pdvReaderNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.reviewNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.signInScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.subjectNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.universityNavGraph
import com.solutionteam.mindfulmentor.ui.main.navigation.welcomeScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        onSeeAllScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        chatBotScreen(onNavigateBack = navController::navigateUp)
        onboardingScreen(onNavigateTo = navController::navigateTo)
        loginNavGraph(
            onNavigateToRoot = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        mainNavGraph(onNavigateToRoot = navController::navigateTo)
        welcomeScreen(onNavigateTo = navController::navigateTo)
        signInScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        individualMeetingNavGraph(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        mentorNavGraph(
            onNavigateToRoot = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        universityNavGraph(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        notificationNavGraph(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        subjectNavGraph(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        reviewNavGraph(onNavigateBack = navController::navigateUp)

        pdvReaderNavGraph(onNavigateBack = navController::navigateUp)
    }
}
