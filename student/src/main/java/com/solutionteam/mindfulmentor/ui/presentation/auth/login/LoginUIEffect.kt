package com.solutionteam.mindfulmentor.ui.presentation.auth.login

sealed interface LoginUIEffect {
    object OnClickLogin :LoginUIEffect
    object OnClickBack :LoginUIEffect
    object LoginError : LoginUIEffect
}
