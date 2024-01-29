package com.solutionteam.mindfulmentor.ui.presentation.auth.welcome


sealed interface WelcomeUiEffect {
    object OnClickLogin:WelcomeUiEffect
    object OnClickSignup:WelcomeUiEffect
    object WelcomeError : WelcomeUiEffect
}