package com.solutionteam.mindfulmentor.ui.auth.signin

data class SignUpUiState(
    val isSignInSuccessful: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val userName: String = "",
    val password: String = "" ,
    val email: String = "",
    val userId: Int? = null,
    val universityName: String = "",
    val field : String = "",
    val level: Int? = null,
)