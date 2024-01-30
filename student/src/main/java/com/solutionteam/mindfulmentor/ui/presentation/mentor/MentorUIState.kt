package com.solutionteam.mindfulmentor.ui.presentation.mentor

data class MentorUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
