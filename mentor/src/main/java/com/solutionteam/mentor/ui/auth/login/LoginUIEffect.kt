package com.solutionteam.mentor.ui.auth.login

sealed interface LoginUIEffect {
    data object LoginError : LoginUIEffect
}
