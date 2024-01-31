package com.solutionteam.mindfulmentor.ui.presentation.auth.welcome

data class WelcomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)