package com.solutionteam.mentor.ui.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mentor.ui.main.navigation.Screen
import com.solutionteam.mentor.ui.main.navigation.homeScreen
import com.solutionteam.mentor.ui.main.navigation.profileScreen
import com.solutionteam.mentor.ui.main.navigation.videosScreen
import com.solutionteam.mentor.ui.main.navigation.walletScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToRoot: (Screen) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        homeScreen(onNavigateToRoot)
        walletScreen(onNavigateToRoot)
        profileScreen(onNavigateToRoot)
        videosScreen(onNavigateToRoot)
    }
}
