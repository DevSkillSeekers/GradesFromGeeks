package com.solutionteam.mindfulmentor.ui.university

import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.ui.home.MentorUiState
import com.solutionteam.mindfulmentor.ui.home.SubjectDetailsUiState
import com.solutionteam.mindfulmentor.ui.home.toSubjectUiState
import com.solutionteam.mindfulmentor.ui.home.toUiState

data class UniversityUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val universityDetails: UniversityDetailsUIState = UniversityDetailsUIState()

)


data class UniversityDetailsUIState(
    val universityName: String = "",
    val universityImageUrl: String = "",
    val universityDescription: String = "",
    val mentorNumber: String = "",
    val summaryNumber: String = "",
    val videoNumber: String = "",
    val subjectList: List<SubjectDetailsUiState> = emptyList(),
    val mentorList: List<MentorUiState> = emptyList()
)

data class ContentCountUIState(
    val count: String = "",
    val contentName: String = "",
)

fun University.toUIState(): UniversityDetailsUIState {
    return UniversityDetailsUIState(
        universityName = name,
        universityImageUrl = imageUrl,
        universityDescription = address,
        mentorNumber = mentorNumber,
        summaryNumber = summaryNumber,
        videoNumber = videoNumber,
        subjectList = subjects.toSubjectUiState(),
        mentorList = mentors.toUiState()
    )
}
