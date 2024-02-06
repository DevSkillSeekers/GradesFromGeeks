package com.solutionteam.mindfulmentor.ui.auth.login

data class LoginUIState(
    val userName: String = "",
    val password: String = "",
    val userId:Int = 0,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
