package com.solutionteam.mindfulmentor.ui.auth.login

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class LoginViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<LoginUIState, LoginUIEffect>(LoginUIState()),LoginInteractionListener {

    init {
        onMakeRequest()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
                delay(1900)
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

    override fun onClickLogin() {
        sendNewEffect(LoginUIEffect.OnClickLogin)
    }

    override fun onClickBack() {
        sendNewEffect(LoginUIEffect.OnClickBack)
    }


}
