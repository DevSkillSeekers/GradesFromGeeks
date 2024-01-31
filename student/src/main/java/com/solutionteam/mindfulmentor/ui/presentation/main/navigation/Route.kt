package com.solutionteam.mindfulmentor.ui.presentation.main.navigation

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.os.bundleOf
import com.solutionteam.mindfulmentor.ui.presentation.seeAll.SeeAllType


const val navigationRouteLogin = "login"
const val navigationRouteOnBoarding = "onboarding"
const val navigationRouteSeeAll = "seeAll"


const val navigationRouteWelcome = "welcome"
const val navigationRouteSignIn = "sign_in"
const val navigationRouteAdditionalInfo = "additional_info"
const val navigationRouteMain = "main"
const val navigationRouteHome = "home"
const val navigationRouteSearch = "search"
const val navigationRouteProfile = "profile"
const val navigationRouteDownloads = "downloads"


sealed class Screen(
    val route: String,
    var routePath: String? = null,
    var clearBackStack: Boolean = false,
    val restoreState: Boolean = true,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    var args: Bundle? = null
) {
    fun withClearBackStack() = apply { clearBackStack = true }

    fun routeWith(path: String) = apply {
        routePath = path
    }

    object Login : Screen(navigationRouteLogin)

    object Welcome : Screen(navigationRouteWelcome)
    object SignIn : Screen(navigationRouteSignIn)
    object AdditionalInfo : Screen(navigationRouteAdditionalInfo)
    object OnBoarding : Screen(navigationRouteOnBoarding)

    object SeeAll : Screen(navigationRouteSeeAll)

    object Main : Screen(navigationRouteMain)
    object Home : Screen(
        route = navigationRouteHome,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object Search : Screen(
        route = navigationRouteSearch,
        selectedIcon = Icons.Rounded.Search,
        unselectedIcon = Icons.Outlined.Search

    )

    object Profile : Screen(
        route = navigationRouteProfile,
        selectedIcon = Icons.Rounded.Person,
        unselectedIcon = Icons.Outlined.Person

    )

    object Downloads : Screen(
        route = navigationRouteDownloads,
        selectedIcon = Icons.Rounded.Build,
        unselectedIcon = Icons.Outlined.Build
    )
}
