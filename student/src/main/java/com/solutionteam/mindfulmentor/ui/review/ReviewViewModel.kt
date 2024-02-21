package com.solutionteam.mindfulmentor.ui.review

import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class ReviewViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<ReviewUIState, ReviewUIEffect>(ReviewUIState()) {

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
            ReviewUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(ReviewUIEffect.ReviewError)
    }

}
