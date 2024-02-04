package com.solutionteam.mindfulmentor.ui.university


sealed interface UniversityUIEffect {
    data object UniversityError : UniversityUIEffect

    data object NavigateToSeeAll : UniversityUIEffect

    data object NavigateToMentorProfile : UniversityUIEffect
}