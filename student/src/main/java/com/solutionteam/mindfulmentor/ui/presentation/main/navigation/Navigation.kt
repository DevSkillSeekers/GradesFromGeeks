package com.solutionteam.mindfulmentor.ui.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mindfulmentor.ui.presentation.auth.login.LoginScreen
import com.solutionteam.mindfulmentor.ui.presentation.auth.login.LoginUIEffect
import com.solutionteam.mindfulmentor.ui.presentation.auth.signin.additionalinfo.AdditionalInformationScreen
import com.solutionteam.mindfulmentor.ui.presentation.auth.signin.maininfo.SignInScreen
import com.solutionteam.mindfulmentor.ui.presentation.auth.welcome.WelcomeScreen
import com.solutionteam.mindfulmentor.ui.presentation.auth.welcome.WelcomeUiEffect
import com.solutionteam.mindfulmentor.ui.presentation.downloads.DownloadsScreen
import com.solutionteam.mindfulmentor.ui.presentation.home.HomeScreen
import com.solutionteam.mindfulmentor.ui.presentation.main.MainScreen
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.graph.MainNavGraph
import com.solutionteam.mindfulmentor.ui.presentation.onboarding.OnBoardingScreen
import com.solutionteam.mindfulmentor.ui.presentation.profile.ProfileScreen
import com.solutionteam.mindfulmentor.ui.presentation.search.SearchScreen


fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Login.route
    ) {

        LoginScreen(
            navigateTo = {
                when (it) {
                    LoginUIEffect.OnClickLogin -> Screen.Login.withClearBackStack()
                        .also(onNavigateToRoot)

                    else -> {}
                }
            },
            onNavigateBack = { onNavigateBack() }
        )

    }
}


fun NavGraphBuilder.mainNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Main.route
    ) {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomBar: @Composable () -> Unit = {
            HRBottomNavigation(
                screens = listOf(
                    Screen.Home,
                    Screen.Search,
                    Screen.Downloads,
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
    composable(
        route = Screen.Home.route
    ) {
        HomeScreen()
    }
}

fun NavGraphBuilder.welcomeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Welcome.route
    )
    {
        WelcomeScreen() {
            when (it) {
                WelcomeUiEffect.OnClickLogin -> Screen.Login.withClearBackStack().also(onNavigateTo)
                WelcomeUiEffect.OnClickSignIn -> Screen.SignIn.withClearBackStack()
                    .also(onNavigateTo)

                else -> {}
            }
        }
    }
}

fun NavGraphBuilder.signInScreen(onNavigateTo: (Screen) -> Unit,onNavigateBack: () -> Unit) {
    composable(
        route = Screen.SignIn.route
    ) {
        SignInScreen(
            navigateTo = {
                Screen.AdditionalInfo.withClearBackStack().also(onNavigateTo)
            },
            onNavigateBack = {onNavigateBack()}
        )
    }
}

fun NavGraphBuilder.additionalInfo(onNavigateTo: (Screen) -> Unit,onNavigateBack: () -> Unit) {
    composable(
        route = Screen.AdditionalInfo.route
    ) {
        AdditionalInformationScreen (
            navigateTo = { Screen.Main.withClearBackStack().also(onNavigateTo) },
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.searchScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Search.route
    ) {
        SearchScreen()
    }
}

fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {
        ProfileScreen()
    }
}

fun NavGraphBuilder.downloadsScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Downloads.route
    ) {
        DownloadsScreen()
    }
}

fun NavGraphBuilder.onboardingScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.OnBoarding.route
    ) {
        OnBoardingScreen()
    }
}
