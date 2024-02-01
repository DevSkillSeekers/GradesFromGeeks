package com.solutionteam.mindfulmentor.ui.auth.login

sealed interface LoginUIEffect {
    data object OnClickLogin :LoginUIEffect
    data object OnClickBack :LoginUIEffect
    data object LoginError : LoginUIEffect
}
