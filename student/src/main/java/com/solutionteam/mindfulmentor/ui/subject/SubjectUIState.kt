package com.solutionteam.mindfulmentor.ui.subject

import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState

data class SubjectUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val contentCount: ContentCountUIState = ContentCountUIState()

)
