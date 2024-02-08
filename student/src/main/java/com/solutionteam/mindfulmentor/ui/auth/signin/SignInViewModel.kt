package com.solutionteam.mindfulmentor.ui.auth.signin

import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.network.repositories.AuthRepository
import com.solutionteam.mindfulmentor.data.network.response.SignInResult
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInUIEffect
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository
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
    fun onClickSignUp() {
        viewModelScope.launch {
            try {
                onLoading()
                val result = authRepository.signUp(state.value.email, state.value.password)
//                authRepository.addStudentInfo()
                onSuccess()
            } catch (e: Exception) {
                onError(e.message?:"error")
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
