package com.solutionteam.mentor.ui.auth.signup

import com.solutionteam.mentor.data.repositories.AuthRepository
import com.solutionteam.mentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mentor.data.source.remote.response.SignInResult
import com.solutionteam.mentor.ui.base.BaseViewModel

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val midFulMentorRepository: MindfulMentorRepository
) : BaseViewModel<SignUpUiState, SignInUIEffect>(SignUpUiState()) {

    fun onSignInResult(result: SignInResult) {
        updateState {
            it.copy(
                isSignInSuccessful = result.data != null,
                errorMessage = result.errorMessage
            )
        }
    }

    private fun onSuccess() {
        updateState {
            it.copy(
                isSignInSuccessful = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                isSignInSuccessful = false,
                errorMessage = "",
                isError = false
            )
        }
    }

    private fun onError(errorMessage: String) {
        updateState {
            SignUpUiState(
                isError = true,
                isLoading = false,
                isSignInSuccessful = false,
                errorMessage = errorMessage
            )
        }
    }

    fun onClickContinue() {
        updateState { it.copy(isScreenContinue = false) }
    }

    fun onClickBackInAdditionalInfo() {
        updateState { it.copy(isScreenContinue = true) }
    }

    fun onClickSignUp() {
        if (state.value.universityName.isNotEmpty() && state.value.field.isNotEmpty()) {
//            onSignUP()
        } else {
            updateState {
                it.copy(
                    isError = true,
                    errorMessage = "University or Field is empty "
                )
            }
        }
    }

    fun clearErrorState() {
        updateState { currentState ->
            currentState.copy(errorMessage = null, isError = false)
        }
    }

    fun onChangeUserName(userName: String) {
        updateState { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        updateState { it.copy(password = password) }
    }

    fun onChangeEmail(email: String) {
        updateState { it.copy(email = email) }
    }

    fun onChangeUniversityName(universityName: String) {
        updateState { it.copy(universityName = universityName) }
    }

    fun onChangeField(field: String) {
        updateState { it.copy(field = field) }
    }

    fun onChangeLevel(level: Int?) {
        updateState { it.copy(level = level) }
    }

}
