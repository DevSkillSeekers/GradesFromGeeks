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
import com.solutionteam.mindfulmentor.ui.auth.signin.additionalinfo.AdditionalInformationScreen
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInScreen
import com.solutionteam.mindfulmentor.ui.auth.welcome.WelcomeScreen
import com.solutionteam.mindfulmentor.ui.auth.welcome.WelcomeUiEffect
import com.solutionteam.mindfulmentor.ui.chat.ChatBotScreen
import com.solutionteam.mindfulmentor.ui.downloads.DownloadsScreen
import com.solutionteam.mindfulmentor.ui.home.HomeScreen
import com.solutionteam.mindfulmentor.ui.home.HomeUIEffect
import com.solutionteam.mindfulmentor.ui.individualMeeting.IndividualMeetingScreen
import com.solutionteam.mindfulmentor.ui.main.MainScreen
import com.solutionteam.mindfulmentor.ui.main.navigation.ext.navigateTo
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.MainNavGraph
import com.solutionteam.mindfulmentor.ui.mentor.MentorScreen
import com.solutionteam.mindfulmentor.ui.mentor.MentorUIEffect
import com.solutionteam.mindfulmentor.ui.onboarding.OnBoardingScreen
import com.solutionteam.mindfulmentor.ui.profile.ProfileScreen
import com.solutionteam.mindfulmentor.ui.search.SearchScreen
import com.solutionteam.mindfulmentor.ui.search.SearchUIEffect
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllScreen
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType
import com.solutionteam.mindfulmentor.ui.seeAll.toSeeAllType
import com.solutionteam.mindfulmentor.ui.subject.SubjectScreen
import com.solutionteam.mindfulmentor.ui.subject.SubjectUIEffect
import com.solutionteam.mindfulmentor.ui.university.UniversityScreen
import com.solutionteam.mindfulmentor.ui.university.UniversityUIEffect


fun NavGraphBuilder.loginNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Login.route
    ) {
        LoginScreen(
            navigateTo = {
                Screen.Main.withClearBackStack().also(onNavigateToRoot)
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
                    HomeUIEffect.NavigateToChatBooks -> Screen.ChatBot.withClearBackStack()
                        .also(onNavigateTo)

                    HomeUIEffect.NavigateToMentorProfile -> Screen.Mentor.withClearBackStack()
                        .also(onNavigateTo)

                    HomeUIEffect.NavigateToNotification -> {}
                    is HomeUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", navigate.type.value))
                        Screen.SeeAll.withClearBackStack().also(onNavigateTo)
                    }

                    HomeUIEffect.NavigateToUniversityProfile -> Screen.University.withClearBackStack()
                        .also(onNavigateTo)

                    HomeUIEffect.NavigateToSubject -> Screen.Subject.withClearBackStack()
                        .also(onNavigateTo)

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

fun NavGraphBuilder.signInScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.SignIn.route
    ) {
        SignInScreen(
            navigateTo = {
                Screen.AdditionalInfo.withClearBackStack().also(onNavigateTo)
            },
            onNavigateBack = { onNavigateBack() }
        )
    }
}

fun NavGraphBuilder.additionalInfo(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.AdditionalInfo.route
    ) {
        AdditionalInformationScreen(
            navigateTo = { Screen.Login.withClearBackStack().also(onNavigateTo) },
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.searchScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Search.route
    ) {
        SearchScreen(
            navigateTo = { navigate ->
                when (navigate) {
                    is SearchUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", navigate.type.value))
                        Screen.SeeAll.withClearBackStack().also(onNavigateTo)
                    }

                    SearchUIEffect.NavigateToMentorProfile -> Screen.Mentor.withClearBackStack()
                        .also(onNavigateTo)

                    SearchUIEffect.NavigateToUniversityProfile -> Screen.University.withClearBackStack()
                        .also(onNavigateTo)

                    SearchUIEffect.NavigateToSubject -> Screen.Subject.withClearBackStack()
                        .also(onNavigateTo)

                    else -> {}
                }
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
        ChatBotScreen(onNavigateBack = onNavigateBack)
    }
}

fun NavGraphBuilder.onSeeAllScreen(onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    this.composable(
        route = Screen.SeeAll.route
    ) {
        val value = Screen.SeeAll.args?.getString("type").toString().toSeeAllType()
        SeeAllScreen(
            type = value,
            navigateTo = {
                when (value) {
                    SeeAllType.Mentors -> Screen.Mentor.withClearBackStack().also(onNavigateTo)
                    SeeAllType.Universities -> Screen.University.withClearBackStack()
                        .also(onNavigateTo)

                    else -> {}
                }
            },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.mentorNavGraph(onNavigateToRoot: (Screen) -> Unit, onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Mentor.route
    ) {

        MentorScreen(
            onNavigateTo = {
                when (it) {
                    is MentorUIEffect.NavigateToScheduleMeeting -> {
                        Screen.IndividualMeeting.also(onNavigateToRoot)
                    }

                    else -> {}
                }
            },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.universityNavGraph(
    onNavigateTo: (Screen) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(
        route = Screen.University.route
    ) {

        UniversityScreen(
            onNavigateTo = {
                when (it) {
                    UniversityUIEffect.NavigateToMentorProfile -> Screen.Mentor.withClearBackStack()
                        .also(onNavigateTo)

                    is UniversityUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", SeeAllType.Mentors.value))
                        Screen.SeeAll.withClearBackStack().also(onNavigateTo)
                    }

                    else -> {}
                }
            },
            navigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.subjectNavGraph(
    onNavigateTo: (Screen) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(
        route = Screen.Subject.route
    ) {

        SubjectScreen(
            onNavigateTo = {
                when (it) {
                    SubjectUIEffect.NavigateToMentorProfile -> Screen.Mentor.withClearBackStack()
                        .also(onNavigateTo)

                    is SubjectUIEffect.NavigateToSeeAll -> {
                        Screen.SeeAll.args = bundleOf(Pair("type", SeeAllType.Mentors.value))
                        Screen.SeeAll.withClearBackStack().also(onNavigateTo)
                    }

                    else -> {}
                }
            },
            navigateBack = onNavigateBack
        )
    }
}


fun NavGraphBuilder.individualMeetingNavGraph(
    onNavigateTo: (Screen) -> Unit, onNavigateBack: () -> Unit
) {
    composable(
        route = Screen.IndividualMeeting.route
    ) {
        IndividualMeetingScreen(
            navigateTo = {},
            navigateBack = onNavigateBack
        )
    }
}
