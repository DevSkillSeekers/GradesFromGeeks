package com.solutionteam.mindfulmentor.ui.downloads

import com.solutionteam.mindfulmentor.data.entity.Download
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class DownloadsViewModel(
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<DownloadsUIState, DownloadsUIEffect>(DownloadsUIState()) {

    init {
        getDataOfDownload()
    }

    private fun getDataOfDownload() {
        updateState { it.copy(isLoading = true) }
        onGetDownloadDetails()
    }

    private fun onGetDownloadDetails() {
        tryToExecute(
            {
                delay(1000)
                ggRepository.getDownloadDetails("1")
            },
            ::onSuccess,
            ::onError
        )
    }

    private fun onSuccess(download: Download) {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
                downloadDetails = download.toUIState()
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
