package com.solutionteam.mindfulmentor.ui.presentation.downloads

data class DownloadsUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
