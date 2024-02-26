package com.solutionteam.mindfulmentor.ui.university

import android.util.Log
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class UniversityViewModel(
    private val id : String,
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<UniversityUIState, UniversityUIEffect>(UniversityUIState()) {

    init {
        Log.i("lllllllllll", id)
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
            UniversityUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(UniversityUIEffect.UniversityError)
    }


}
