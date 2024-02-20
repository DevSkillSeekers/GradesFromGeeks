package com.solutionteam.mentor.ui.main.navigation

import android.os.Bundle
import com.solutionteam.mentor.R


const val navigationRouteMain = "main"
const val navigationRouteSignUp = "signUp"

const val navigationRouteHome = "home"
const val navigationRouteWallet = "wallet"
const val navigationRouteProfile = "profile"
const val navigationRouteVideos = "videos"



sealed class Screen(
    val route: String,
    var routePath: String? = null,
    var clearBackStack: Boolean = false,
    val restoreState: Boolean = true,
    val selectedIcon: Int? = null,
    val unselectedIcon: Int? = null,
    var args: Bundle? = null
) {
    fun withClearBackStack() = apply { clearBackStack = true }

    fun routeWith(path: String) = apply {
        routePath = path
    }

    data object SignUp : Screen(navigationRouteSignUp)

    data object Main : Screen(navigationRouteMain)
    data object Home : Screen(
        route = navigationRouteHome,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected
    )

    data object Wallet : Screen(
        route = navigationRouteWallet,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_wallet_unselected

    )

    data object Profile : Screen(
        route = navigationRouteProfile,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected
    )

    data object Videos : Screen(
        route = navigationRouteVideos,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_video_unselected
    )

}
