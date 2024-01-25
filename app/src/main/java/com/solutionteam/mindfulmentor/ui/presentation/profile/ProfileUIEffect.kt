package com.solutionteam.mindfulmentor.ui.presentation.profile

sealed interface ProfileUIEffect {
    object ProfileError : ProfileUIEffect
}