package com.solutionteam.mindfulmentor.ui.main.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mindfulmentor.ui.auth.login.LoginScreen
import com.solutionteam.mindfulmentor.ui.auth.login.LoginUIEffect
import com.solutionteam.mindfulmentor.ui.auth.signin.additionalinfo.AdditionalInformationScreen
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInScreen
import com.solutionteam.mindfulmentor.ui.auth.welcome.WelcomeScreen
import com.solutionteam.mindfulmentor.ui.auth.welcome.WelcomeUiEffect
import com.solutionteam.mindfulmentor.ui.chat.ChatBotScreen
import com.solutionteam.mindfulmentor.ui.downloads.DownloadsScreen
import com.solutionteam.mindfulmentor.ui.home.HomeScreen
import com.solutionteam.mindfulmentor.ui.home.HomeUIEffect
import com.solutionteam.mindfulmentor.ui.main.MainScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.MainNavGraph
import com.solutionteam.mindfulmentor.ui.mentor.MentorScreen
import com.solutionteam.mindfulmentor.ui.onboarding.OnBoardingScreen
import com.solutionteam.mindfulmentor.ui.profile.ProfileScreen
import com.solutionteam.mindfulmentor.ui.search.SearchScreen
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllScreen
import com.solutionteam.mindfulmentor.ui.seeAll.toSeeAllType
import com.solutionteam.mindfulmentor.ui.university.UniversityScreen


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
                    HomeUIEffect.NavigateToChatBooks -> Screen.ChatBot.also(onNavigateTo)
                    HomeUIEffect.NavigateToMentorProfile -> Screen.Mentor.also(onNavigateTo)
                    HomeUIEffect.NavigateToNotification -> {}
                    is HomeUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", navigate.type.value))
                        Screen.SeeAll.also(onNavigateTo)
                    }
                    HomeUIEffect.NavigateToUniversityProfile -> Screen.University.also(onNavigateTo)
                    else -> {}
                }
            },
        )
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

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
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
        OnBoardingScreen(
            navigateTo = { Screen.Welcome.withClearBackStack().also(onNavigateTo) }
        )
    }
}
fun NavGraphBuilder.chatBotScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.ChatBot.route
    ) {
        ChatBotScreen(onNavigateBack =  onNavigateBack)
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
            onNavigateTo = { },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.universityNavGraph(
    onNavigateToRoot: (Screen) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(
        route = Screen.University.route
    ) {

        UniversityScreen(
            onNavigateTo = { },
            navigateBack = onNavigateBack
        )
    }
}
