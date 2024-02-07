package com.solutionteam.mindfulmentor.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.network.repositories.AuthRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginUIState, LoginUIEffect>(LoginUIState()) {

    private fun onSuccess() {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                isSuccess = false,
                errorMessage = "",
                isError = false
            )
        }
    }

    private fun onError(errorMessage: String) {
        updateState {
            LoginUIState(
                isError = true,
                isLoading = false,
                isSuccess = false,
                errorMessage = errorMessage
            )
        }
        sendNewEffect(LoginUIEffect.LoginError)
    }

    fun onChangeUserName(userName: String) {
        updateState { it.copy(email = userName) }
    }

    fun onChangePassword(password: String) {
        updateState { it.copy(password = password) }
    }

    fun onClickLogin() {
        viewModelScope.launch {
            try {
                onLoading()
                authRepository.signIn(state.value.email, state.value.password)
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
}