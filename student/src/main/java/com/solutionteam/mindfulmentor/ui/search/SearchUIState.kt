package com.solutionteam.mindfulmentor.ui.search

import com.solutionteam.mindfulmentor.data.entity.SearchResult
import com.solutionteam.mindfulmentor.ui.home.MentorUiState
import com.solutionteam.mindfulmentor.ui.home.SubjectDetailsUiState
import com.solutionteam.mindfulmentor.ui.home.UniversityUiState
import com.solutionteam.mindfulmentor.ui.home.toSubjectUiState
import com.solutionteam.mindfulmentor.ui.home.toUiState
import com.solutionteam.mindfulmentor.ui.home.toUniversityUiState

data class SearchUIState(
    val keyword: String = "",
    val universities: List<UniversityUiState> = emptyList(),
    val mentors: List<MentorUiState> = emptyList(),
    val subjects: List<SubjectDetailsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
fun SearchResult.toUIState(): SearchUIState {
    return SearchUIState(
        universities = universities.toUniversityUiState(),
        mentors = mentors.toUiState(),
        subjects = subject.toSubjectUiState(),
    )
}