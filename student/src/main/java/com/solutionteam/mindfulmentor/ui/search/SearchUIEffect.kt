package com.solutionteam.mindfulmentor.ui.search

import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType

sealed interface SearchUIEffect {
    data object SearchError : SearchUIEffect
    data class NavigateToSeeAll(val type: SeeAllType) : SearchUIEffect
    data class NavigateToMentorProfile (val id: String) : SearchUIEffect
    data class NavigateToUniversityProfile(val id: String) : SearchUIEffect
    data class NavigateToSubject(val id: String) : SearchUIEffect
}