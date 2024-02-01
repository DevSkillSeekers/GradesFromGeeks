package com.solutionteam.mindfulmentor.ui.profile

data class ProfileUIState(
    val isLoading: Boolean = false,

    val profileUrl: String = "",
    val name: String = "",

    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
