package com.solutionteam.mindfulmentor.ui.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mindfulmentor.ui.presentation.auth.login.LoginScreen
import com.solutionteam.mindfulmentor.ui.presentation.downloads.DownloadsScreen
import com.solutionteam.mindfulmentor.ui.presentation.home.HomeScreen
import com.solutionteam.mindfulmentor.ui.presentation.home.HomeUIEffect
import com.solutionteam.mindfulmentor.ui.presentation.main.MainScreen
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.presentation.main.navigation.graph.MainNavGraph
import com.solutionteam.mindfulmentor.ui.presentation.mentor.MentorScreen
import com.solutionteam.mindfulmentor.ui.presentation.onboarding.OnBoardingScreen
import com.solutionteam.mindfulmentor.ui.presentation.profile.ProfileScreen
import com.solutionteam.mindfulmentor.ui.presentation.search.SearchScreen
import com.solutionteam.mindfulmentor.ui.presentation.seeAll.SeeAllScreen
import com.solutionteam.mindfulmentor.ui.presentation.seeAll.toSeeAllType


fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Login.route
    ) {

        LoginScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateToRoot)
            }
        )
    }
}


fun NavGraphBuilder.mainNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(route = Screen.Main.route) {

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
    composable(route = Screen.Home.route) {
        HomeScreen(
            navigateTo = { navigate ->
                when (navigate) {
                    HomeUIEffect.NavigateToChatBooks -> {}
                    HomeUIEffect.NavigateToMentorProfile -> Screen.Mentor.also(onNavigateTo)
                    HomeUIEffect.NavigateToNotification -> {}
                    is HomeUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", navigate.type.value))
                        Screen.SeeAll.also(onNavigateTo)
                    }
                    HomeUIEffect.NavigateToUniversityProfile -> {}
                    else -> {}
                }
            }
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

fun NavGraphBuilder.onSeeAllScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    this.composable(
        route = Screen.SeeAll.route
    ) {
        val value = Screen.SeeAll.args?.getString("type").toString().toSeeAllType()
        SeeAllScreen(
            type = value,
            navigateTo = { Screen.Mentor.withClearBackStack().also(onNavigateTo) },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.mentorNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Mentor.route
    ) {

        MentorScreen(
            onNavigateTo = {  },
            navigateBack = onNavigateBack
        )
    }
}
