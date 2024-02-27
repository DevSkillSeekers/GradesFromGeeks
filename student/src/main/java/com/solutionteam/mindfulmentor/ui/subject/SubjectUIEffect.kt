package com.solutionteam.mindfulmentor.ui.subject


sealed interface SubjectUIEffect {

    data object SubjectError : SubjectUIEffect

    data object NavigateToSeeAll : SubjectUIEffect

    data class NavigateToMentorProfile(val id: String) : SubjectUIEffect
}