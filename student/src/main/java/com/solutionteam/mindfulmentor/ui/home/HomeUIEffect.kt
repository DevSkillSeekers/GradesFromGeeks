package com.solutionteam.mindfulmentor.ui.home

import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType

sealed interface HomeUIEffect {
    data object HomeError : HomeUIEffect

    data class NavigateToSeeAll(val type: SeeAllType) : HomeUIEffect

    data object NavigateToMentorProfile : HomeUIEffect
    data object NavigateToUniversityProfile : HomeUIEffect

    data object NavigateToNotification : HomeUIEffect

    data object NavigateToChatBooks : HomeUIEffect

    data object NavigateToSubject : HomeUIEffect

}
