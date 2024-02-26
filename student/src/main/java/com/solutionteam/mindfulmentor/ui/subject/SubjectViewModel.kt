package com.solutionteam.mindfulmentor.ui.subject

import android.util.Log
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class SubjectViewModel(
    val id: String,
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<SubjectUIState, SubjectUIEffect>(SubjectUIState()) {

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
            SubjectUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(SubjectUIEffect.SubjectError)
    }


}
