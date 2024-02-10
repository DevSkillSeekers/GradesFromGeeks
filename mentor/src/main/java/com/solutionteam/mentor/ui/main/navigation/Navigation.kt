package com.solutionteam.mentor.ui.main.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mentor.ui.main.MainScreen
import com.solutionteam.mentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mentor.ui.main.navigation.graph.MainNavGraph
import com.solutionteam.mentor.ui.videos.VideosScreen
import com.solutionteam.mentor.ui.home.HomeScreen
import com.solutionteam.mentor.ui.profile.ProfileScreen
import com.solutionteam.mentor.ui.wallet.WalletScreen

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun NavGraphBuilder.mainNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(route = Screen.Main.route) {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomBar: @Composable () -> Unit = {
            HRBottomNavigation(
                screens = listOf(
                    Screen.Home,
                    Screen.Wallet,
                    Screen.Videos,
                    Screen.Profile,
                ),
                onNavigateTo = navController::navigateTo,
                currentDestination = navBackStackEntry?.destination
            )
        }

        val nestedNavGraph: @Composable () -> Unit = {
            MainNavGraph(
                navController = navController,
                onNavigateToRoot = onNavigateToRoot
            )
        }

        MainScreen(
            bottomBar = bottomBar,
            nestedNavGraph = nestedNavGraph
        )
    }

}

fun NavGraphBuilder.homeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(route = Screen.Home.route) {
        HomeScreen(
            navigateTo = { navigate ->
            },
        )
    }
}

fun NavGraphBuilder.walletScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Wallet.route
    ) {
        WalletScreen(
            navigateTo = { navigate ->

            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {
        ProfileScreen()
    }
}

fun NavGraphBuilder.videosScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Videos.route
    ) {
        VideosScreen()
    }
}
