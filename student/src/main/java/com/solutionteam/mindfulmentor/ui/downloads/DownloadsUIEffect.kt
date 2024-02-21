package com.solutionteam.mindfulmentor.ui.downloads

sealed interface DownloadsUIEffect {
    data object DownloadsError : DownloadsUIEffect

    data object NavigateToReviewScreen : DownloadsUIEffect
    data object NavigateToPDFReader : DownloadsUIEffect
}
