package com.solutionteam.mindfulmentor.ui.subject

import com.solutionteam.mindfulmentor.ui.home.MentorUiState
import com.solutionteam.mindfulmentor.ui.home.SubjectDetailsUiState
import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState

data class SubjectUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val contentCount: ContentCountUIState = ContentCountUIState(),
    val subjectDetails: SubjectDetailsUiState = SubjectDetailsUiState(),
    val subjectMentors: List<MentorUiState> = emptyList()
)
