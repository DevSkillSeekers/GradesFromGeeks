package com.solutionteam.mentor.ui.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mentor.ui.main.navigation.Screen
import com.solutionteam.mentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mentor.ui.main.navigation.loginNavGraph
import com.solutionteam.mentor.ui.main.navigation.mainNavGraph
import com.solutionteam.mentor.ui.main.navigation.signInScreen
import com.solutionteam.mentor.ui.main.navigation.welcomeScreen

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
        mainNavGraph(onNavigateToRoot = navController::navigateTo)

        loginNavGraph(
            onNavigateToRoot = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
        welcomeScreen(onNavigateTo = navController::navigateTo)
        signInScreen(
            onNavigateTo = navController::navigateTo,
            onNavigateBack = navController::navigateUp
        )
    }
}
