package com.solutionteam.mindfulmentor.ui.presentation.mentor

sealed interface MentorUIEffect {
    object MentorError : MentorUIEffect
}