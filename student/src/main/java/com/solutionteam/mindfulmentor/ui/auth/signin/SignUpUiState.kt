package com.solutionteam.mindfulmentor.ui.auth.signin

data class SignUpUiState(
    val isSignInSuccessful: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val universityName: String = "",
    val field : String = "",
    val level: Int? = 1,
    val universities: List<String> = emptyList(),
    val fields : List<String> = emptyList(),
    val levels: List<String> = emptyList(),
    val isScreenContinue: Boolean = true,
    val userId: String? = null,
    val profilePictureUrl: String = ""
)
