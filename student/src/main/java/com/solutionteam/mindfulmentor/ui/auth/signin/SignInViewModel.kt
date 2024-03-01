package com.solutionteam.mindfulmentor.ui.auth.signin

import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.entity.StudentInfo
import com.solutionteam.mindfulmentor.data.repositories.AuthRepository
import com.solutionteam.mindfulmentor.data.repositories.GoogleAuthUiClient
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.data.source.remote.response.SignInResult
import com.solutionteam.mindfulmentor.data.utils.UserAlreadyExistsException
import com.solutionteam.mindfulmentor.ui.auth.signin.maininfo.SignInUIEffect
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val googleAuthUiClient: GoogleAuthUiClient
) : BaseViewModel<SignUpUiState, SignInUIEffect>(SignUpUiState()) {


    fun onClickGoogle() {
        viewModelScope.launch {
            try {
                Log.e("TAG", "onClickGoogle: ")
                val intentSender = googleAuthUiClient.signInWithGoogle()
                sendNewEffect(SignInUIEffect.GoogleSignIn(intentSender ?: return@launch))
            } catch (e: Exception) {
                Log.e("TAG", "signInWithGoogle: ${e.message}")
            }
        }
    }

    fun onGoogleSignInResult(intent: Intent?) {
        viewModelScope.launch {
            try {
                Log.e("TAG", "onGoogleSignInResult: ")
                googleAuthUiClient.signInWithIntent(intent ?: return@launch).also { result ->
                    result.data?.let { user->
                       if (authRepository.checkUserExist(user.email)) {
                           Log.e("TAG", "onGoogleSignInResult: user not found")
                           updateState {
                               it.copy(
                                       isScreenContinue = false,
                                       userId = user.userId,
                                       profilePictureUrl = user.profilePictureUrl
                                           ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
                                       userName = user.username ?: "",
                                       email = user.email ?: ""
                               )
                           }
                       }else {
                           onSuccess()
                       }
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "onGoogleSignInResult: ${e.message}")
            }
        }
    }

    private fun onSuccess() {
        Log.e("TAG", "onSuccess: ")
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

    private suspend fun signUpByEmailAndPassword(studentInfo:StudentInfo): Boolean {
        return authRepository.signUp(state.value.email, state.value.password,studentInfo)
    }

    private fun onSignUP() {
        viewModelScope.launch {
            try {
                onLoading()
                val studentInfo = StudentInfo(
                        userName = state.value.userName,
                        universityName = state.value.universityName,
                        field = state.value.field,
                        level = state.value.level,
                        imageUrl = state.value.profilePictureUrl,
                        email = state.value.email
                )
                if (state.value.email.isEmpty() || state.value.password.isEmpty()) {
                    Log.e("TAG", "signInWithInfo: ${state.value.userId}")
                    if (signInWithInfo(studentInfo, state.value.userId ?: "")) onSuccess()
                } else {
                    Log.e("TAG", "signUpByEmailAndPassword: ${state.value.userId}")
                    if (signUpByEmailAndPassword(studentInfo)) onSuccess()
                }
            } catch (e: UserAlreadyExistsException) {
                onError(e.message ?: "error")
            } catch (e: Exception) {
                onError(e.message ?: "error")
            }
        }
    }

    private suspend fun signInWithInfo(studentInfo: StudentInfo, userId:String): Boolean {
          return authRepository.addStudentInfo(studentInfo,userId)
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
