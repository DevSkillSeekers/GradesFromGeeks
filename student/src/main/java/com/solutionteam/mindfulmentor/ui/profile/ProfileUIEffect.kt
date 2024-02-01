package com.solutionteam.mindfulmentor.ui.profile

sealed interface ProfileUIEffect {
    data object ProfileError : ProfileUIEffect
}