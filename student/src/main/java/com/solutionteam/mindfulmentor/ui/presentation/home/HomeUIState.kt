package com.solutionteam.mindfulmentor.ui.presentation.home

import com.solutionteam.mindfulmentor.data.entity.Mentor

data class HomeUIState(

    val mentors: List<MentorUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)


data class MentorUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val rate: Double = 0.0,
    val numberReviewers: Int = 0
)


//region Mappers

fun Mentor.toUiState() = MentorUiState(
    id = id,
    name = name,
    imageUrl = imageUrl,
    rate = rate,
    numberReviewers = numberReviewers
)


fun List<Mentor>.toUiState() = map { it.toUiState() }

//endregion
