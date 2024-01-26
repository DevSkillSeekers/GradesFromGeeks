package com.solutionteam.mindfulmentor.ui.presentation.home

data class HomeUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
