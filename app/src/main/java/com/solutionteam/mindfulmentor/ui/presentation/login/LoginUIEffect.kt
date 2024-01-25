package com.solutionteam.mindfulmentor.ui.presentation.login

sealed interface LoginUIEffect {
    object LoginError : LoginUIEffect
}