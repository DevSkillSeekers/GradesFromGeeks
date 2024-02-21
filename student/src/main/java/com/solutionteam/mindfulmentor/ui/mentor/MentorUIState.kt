package com.solutionteam.mindfulmentor.ui.mentor

data class MentorUIState(
    val fullName: String = "Ahmed Ali",
    val imageUrl: String = "https://www.crushpixel.com/big-static7/preview4/portrait-male-student-working-on-260856.jpg",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)


data class SummeryDetailsUIState(
    val chapterNumber: String,
    val chapterDescription: String,
    val piedPrice: String,
)

