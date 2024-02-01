package com.solutionteam.mindfulmentor.ui.auth.signin

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.data.network.response.SignInResult
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInUIEffect
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel

class SignInViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<SignInState, SignInUIEffect>(SignInState()) {

    fun onSignInResult(result: SignInResult) {
        updateState {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

}
