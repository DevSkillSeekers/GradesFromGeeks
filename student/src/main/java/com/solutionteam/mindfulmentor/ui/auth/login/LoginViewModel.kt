package com.solutionteam.mindfulmentor.ui.auth.login

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class LoginViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<LoginUIState, LoginUIEffect>(LoginUIState()) {

    init {
        onMakeRequest()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            {
                delay(1000)
                updateState { it.copy(isLoading = false, isSuccess = true) }
            },
            { onSuccess() },
            ::onError
        )
    }


    private fun onSuccess() {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onError() {
        updateState {
            LoginUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(LoginUIEffect.LoginError)
    }

    fun onChangeUserName(userName: String) {
        updateState { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        updateState{ it.copy(password = password) }
    }

     fun onClickLogin() {
        sendNewEffect(LoginUIEffect.OnClickLogin)
    }


}
