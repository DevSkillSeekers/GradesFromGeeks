package com.solutionteam.mindfulmentor.ui.auth.login

sealed interface LoginUIEffect {
    data object LoginError : LoginUIEffect
}
