package com.solutionteam.mindfulmentor.ui.presentation.downloads

sealed interface DownloadsUIEffect {
    object DownloadsError : DownloadsUIEffect
}
