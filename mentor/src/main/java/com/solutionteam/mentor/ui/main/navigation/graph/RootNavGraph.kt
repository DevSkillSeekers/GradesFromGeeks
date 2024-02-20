package com.solutionteam.mentor.ui.main.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mentor.ui.main.navigation.Screen
import com.solutionteam.mentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mentor.ui.main.navigation.mainNavGraph
import com.solutionteam.mentor.ui.main.navigation.signUpScreen

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
        signUpScreen(onNavigateTo = navController::navigateTo)
    }
}
