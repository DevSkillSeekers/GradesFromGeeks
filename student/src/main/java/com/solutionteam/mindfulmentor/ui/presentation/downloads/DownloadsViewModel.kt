package com.solutionteam.mindfulmentor.ui.presentation.downloads

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class DownloadsViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<DownloadsUIState, DownloadsUIEffect>(DownloadsUIState()) {

    init {
        onMakeRequest()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
                delay(1900)
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
            DownloadsUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(DownloadsUIEffect.DownloadsError)
    }


}
