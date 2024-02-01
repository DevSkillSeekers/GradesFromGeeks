package com.solutionteam.mindfulmentor.ui.presentation.mentor

data class MentorUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)


data class SummeryDetailsUIState(
    val chapterNumber: String,
    val chapterDescription: String,
    val piedPrice: String,
)

