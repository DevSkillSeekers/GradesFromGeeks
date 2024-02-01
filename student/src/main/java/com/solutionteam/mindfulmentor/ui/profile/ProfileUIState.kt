package com.solutionteam.mindfulmentor.ui.profile

data class ProfileUIState(

    val profileUrl: String = "",
    val name: String = "",

    val showBottomSheetThem: Boolean = false,
    val isDarkTheme: Boolean = false,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
