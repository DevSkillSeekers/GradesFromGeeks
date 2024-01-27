package com.solutionteam.mindfulmentor.ui.presentation.auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
