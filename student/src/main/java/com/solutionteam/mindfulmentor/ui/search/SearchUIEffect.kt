package com.solutionteam.mindfulmentor.ui.search

import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType

sealed interface SearchUIEffect {
    data object SearchError : SearchUIEffect
    data class NavigateToSeeAll(val type: SeeAllType) : SearchUIEffect
    data object NavigateToMentorProfile : SearchUIEffect
    data object NavigateToUniversityProfile : SearchUIEffect
    data object NavigateToSubject : SearchUIEffect
}