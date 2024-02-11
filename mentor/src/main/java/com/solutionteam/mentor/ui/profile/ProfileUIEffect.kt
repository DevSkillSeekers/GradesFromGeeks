package com.solutionteam.mentor.ui.profile

sealed interface ProfileUIEffect {
    data object ProfileError : ProfileUIEffect
}
