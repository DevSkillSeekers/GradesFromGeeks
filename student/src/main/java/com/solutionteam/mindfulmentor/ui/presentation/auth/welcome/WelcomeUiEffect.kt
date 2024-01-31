package com.solutionteam.mindfulmentor.ui.presentation.auth.welcome


sealed interface WelcomeUiEffect {
    object OnClickLogin:WelcomeUiEffect
    object OnClickSignIn:WelcomeUiEffect
    object WelcomeError : WelcomeUiEffect

}