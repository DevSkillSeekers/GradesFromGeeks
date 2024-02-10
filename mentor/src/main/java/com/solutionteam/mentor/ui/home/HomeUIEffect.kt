package com.solutionteam.mentor.ui.home

sealed interface HomeUIEffect {
    data object HomeError : HomeUIEffect

}
