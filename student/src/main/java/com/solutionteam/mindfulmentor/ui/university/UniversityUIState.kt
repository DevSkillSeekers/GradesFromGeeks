package com.solutionteam.mindfulmentor.ui.university

data class UniversityUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)


data class UniversitySummeryDetailsUIState(
    val chapterNumber: String,
    val chapterDescription: String,
    val piedPrice: String,
)

