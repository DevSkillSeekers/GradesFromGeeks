package com.solutionteam.mindfulmentor.ui.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.downloadsScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.homeScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.profileScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.searchScreen

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
        searchScreen(onNavigateToRoot)
        profileScreen(onNavigateToRoot)
        downloadsScreen(onNavigateToRoot)
    }
}
