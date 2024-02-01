package com.solutionteam.mindfulmentor.ui.presentation.mentor

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class MentorViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<MentorUIState, MentorUIEffect>(MentorUIState()) {

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
            MentorUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(MentorUIEffect.MentorError)
    }


}