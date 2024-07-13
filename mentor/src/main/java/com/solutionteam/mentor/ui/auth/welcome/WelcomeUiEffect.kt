package com.solutionteam.mentor.ui.auth.welcome


sealed interface WelcomeUiEffect {
    data object OnClickLogin: WelcomeUiEffect
    data object OnClickSignIn: WelcomeUiEffect
    data object WelcomeError : WelcomeUiEffect

}