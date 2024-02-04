package com.solutionteam.mindfulmentor.ui.university

sealed interface UniversityUIEffect {
    data object UniversityError : UniversityUIEffect
}