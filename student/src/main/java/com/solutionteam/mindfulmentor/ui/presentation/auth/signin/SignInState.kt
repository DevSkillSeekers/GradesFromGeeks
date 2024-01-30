package com.solutionteam.mindfulmentor.ui.presentation.auth.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)
