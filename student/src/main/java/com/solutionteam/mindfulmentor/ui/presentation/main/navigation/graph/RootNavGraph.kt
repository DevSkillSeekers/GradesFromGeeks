package com.solutionteam.mindfulmentor.ui.presentation.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.homeScreen
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.loginNavGraph
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.mainNavGraph

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

        loginNavGraph (onNavigateToRoot = navController::navigateTo)
        mainNavGraph (onNavigateToRoot = navController::navigateTo)
    }
}