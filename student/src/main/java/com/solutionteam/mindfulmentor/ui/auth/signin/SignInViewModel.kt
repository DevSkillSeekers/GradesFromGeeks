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
    fun onChangeUserName(userName: String) {
        updateState { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        updateState { it.copy(password = password) }
    }
    fun onChangeEmail(email: String) {
        updateState { it.copy(email = email) }
    }

    fun onChangeUserId(userId: Int?) {
        updateState { it.copy(userId = userId) }
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
