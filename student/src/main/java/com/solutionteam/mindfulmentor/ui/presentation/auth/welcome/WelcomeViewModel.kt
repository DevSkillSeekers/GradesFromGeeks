package com.solutionteam.mindfulmentor.ui.presentation.auth.welcome

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel

class WelcomeViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
):BaseViewModel<WelcomeUiState,WelcomeUiEffect>(WelcomeUiState()){
    init {
        onSuccess()
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
     fun onClickLogin(){
        sendNewEffect(WelcomeUiEffect.OnClickLogin)
    }
     fun onClickSignIn(){
        sendNewEffect(WelcomeUiEffect.OnClickSignIn)
    }

}