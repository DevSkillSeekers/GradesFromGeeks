package com.solutionteam.mindfulmentor.ui.auth.signin

import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.entity.StudentInfo
import com.solutionteam.mindfulmentor.data.network.repositories.AuthRepository
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.data.network.response.SignInResult
import com.solutionteam.mindfulmentor.data.utils.UserAlreadyExistsException
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInUIEffect
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel(
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
        if (
            validateEmail(state.value.email)
            && validatePassword(state.value.password)
            && validateUserName(state.value.userName)
        ) {
            updateState { it.copy(isScreenContinue = false) }
        } else {
            updateState {
                it.copy(
                    isError = true,
                    errorMessage = "email, password or username is not invalid "
                )
            }
        }
    }

    fun onClickBackInAdditionalInfo() {
        updateState { it.copy(isScreenContinue = true) }
    }

    fun onClickSignUp() {
        if (state.value.universityName.isNotEmpty() && state.value.field.isNotEmpty()) {
            onSignUP()
        } else {
            updateState {
                it.copy(
                    isError = true,
                    errorMessage = "University or Field is empty "
                )
            }
        }
    }
    private fun onSignUP() {
        viewModelScope.launch {
            try {
                onLoading()
                val result =
                    authRepository.signUp(state.value.email, state.value.password)
                val studentInfo = StudentInfo(
                    userName = state.value.userName,
                    universityName = state.value.universityName,
                    field = state.value.field,
                    level = state.value.level,
                    imageUrl = ""
                )
                val studentInfoResult =
                    authRepository.addStudentInfo(studentInfo = studentInfo, user = result.user!!)
                if (studentInfoResult)
                    onSuccess()

            } catch (e: UserAlreadyExistsException) {
                onError(e.message ?: "error")
            } catch (e: Exception) {
                onError(e.message ?: "error")
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

    private fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.length >= 6
    }

}
