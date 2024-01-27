package com.solutionteam.mindfulmentor.ui.presentation.home

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University

data class HomeUIState(

    val mentors: List<MentorUiState> = emptyList(),
    val subjects: List<SubjectUiState> = emptyList(),
    val university: List<UniversityUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


data class MentorUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val rate: Double = 0.0,
    val numberReviewers: Int = 0
)

data class SubjectUiState(
    val id: String = "",
    val name: String = ""
)


data class UniversityUiState(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val imageUrl: String = ""
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

fun Subject.toSubjectUiState() = SubjectUiState(id, name)

fun List<Subject>.toSubjectUiState() = map { it.toSubjectUiState() }


fun University.toUniversityUiState() = UniversityUiState(
    id = id,
    imageUrl = imageUrl,
    name = name,
    address = address
)

fun List<University>.toUniversityUiState() = map { it.toUniversityUiState() }

//endregion
