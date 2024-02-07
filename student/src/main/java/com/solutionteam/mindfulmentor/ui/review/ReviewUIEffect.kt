package com.solutionteam.mindfulmentor.ui.review


sealed interface ReviewUIEffect {

    data object ReviewError : ReviewUIEffect

    data object NavigateToSeeAll : ReviewUIEffect

    data object NavigateToMentorProfile : ReviewUIEffect
}