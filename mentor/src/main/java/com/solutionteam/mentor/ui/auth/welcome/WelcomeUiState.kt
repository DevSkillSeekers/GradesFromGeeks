package com.solutionteam.mentor.ui.auth.welcome

data class WelcomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)