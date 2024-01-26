package com.solutionteam.mindfulmentor.ui.presentation.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.solutionteam.mindfulmentor.R


const val navigationRouteLogin = "login"


const val navigationRouteMain = "main"
const val navigationRouteHome = "home"
const val navigationRouteSearch = "search"
const val navigationRouteProfile = "profile"


sealed class Screen(
    val route: String,
    var routePath: String? = null,
    var clearBackStack: Boolean = false,
    val restoreState: Boolean = true,
    val title: Int = 0,
    val icon: ImageVector? = null
) {
    fun withClearBackStack() = apply { clearBackStack = true }

    fun routeWith(path: String) = apply {
        routePath = path
    }

    object Login : Screen(navigationRouteLogin)


    object Main : Screen(navigationRouteMain)
    object Home : Screen(
        route = navigationRouteHome,
        title = R.string.home_title,
        icon = Icons.Rounded.Home
    )
    object Search : Screen(
        route = navigationRouteSearch,
        title = R.string.search_title,
        icon = Icons.Rounded.Search
    )
    object Profile : Screen(
        route = navigationRouteProfile,
        title = R.string.profile_title,
        icon = Icons.Rounded.Person
    )
}