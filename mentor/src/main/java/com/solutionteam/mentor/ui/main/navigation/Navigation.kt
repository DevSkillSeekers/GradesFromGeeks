package com.solutionteam.mentor.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mentor.ui.auth.login.LoginScreen
import com.solutionteam.mentor.ui.auth.signin.maininfo.SignInScreen
import com.solutionteam.mentor.ui.auth.welcome.WelcomeScreen
import com.solutionteam.mentor.ui.auth.welcome.WelcomeUiEffect
import com.solutionteam.mentor.ui.home.HomeScreen
import com.solutionteam.mentor.ui.main.MainScreen
import com.solutionteam.mentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mentor.ui.main.navigation.graph.MainNavGraph
import com.solutionteam.mentor.ui.profile.ProfileScreen
import com.solutionteam.mentor.ui.videos.VideosScreen
import com.solutionteam.mentor.ui.wallet.WalletScreen

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

//region OnBoarding
fun NavGraphBuilder.welcomeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Welcome.route
    )
    {
        WelcomeScreen {
            when (it) {
                WelcomeUiEffect.OnClickLogin -> Screen.Login.withClearBackStack().also(onNavigateTo)
                WelcomeUiEffect.OnClickSignIn -> Screen.SignIn.withClearBackStack()
                    .also(onNavigateTo)

                else -> {}
            }
        }
    }
}

fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Login.route
    ) {
        LoginScreen(
            navigateTo = { Screen.Main.withClearBackStack().also(onNavigateToRoot) },
            onNavigateBack = { onNavigateBack() }
        )
    }
}

fun NavGraphBuilder.signInScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.SignIn.route
    ) {
        SignInScreen(
            navigateTo = {},
            onNavigateBack = { onNavigateBack() }
        )
    }
}

//endregion