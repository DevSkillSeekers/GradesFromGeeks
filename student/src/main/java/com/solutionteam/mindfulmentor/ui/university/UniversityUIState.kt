package com.solutionteam.mindfulmentor.ui.university

data class UniversityUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val contentCount: ContentCountUIState = ContentCountUIState()

)


data class UniversitySummeryDetailsUIState(
    val chapterNumber: String,
    val chapterDescription: String,
    val piedPrice: String,
)

data class ContentCountUIState(
    val count: String = "",
    val contentName: String = "",
)

