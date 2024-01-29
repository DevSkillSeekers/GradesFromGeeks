package com.solutionteam.mindfulmentor.ui.presentation.auth.signin

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.data.network.response.SignInResult
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel

class SignInViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<SignInState,SignInUIEffect>(SignInState()) {

    fun onSignInResult(result: SignInResult) {
        updateState {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

}
