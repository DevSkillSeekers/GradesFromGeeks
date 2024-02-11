package com.solutionteam.mindfulmentor.ui.downloads

data class DownloadsUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val showReviewBottomSheet: Boolean = false
)
