package com.solutionteam.mindfulmentor.ui.auth.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)
