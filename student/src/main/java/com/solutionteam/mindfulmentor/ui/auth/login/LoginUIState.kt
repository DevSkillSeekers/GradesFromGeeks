package com.solutionteam.mindfulmentor.ui.auth.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
