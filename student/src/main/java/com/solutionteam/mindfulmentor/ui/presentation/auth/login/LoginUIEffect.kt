package com.solutionteam.mindfulmentor.ui.presentation.auth.login

sealed interface LoginUIEffect {
    object LoginError : LoginUIEffect
}
