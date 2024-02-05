package com.solutionteam.mindfulmentor.ui.mentor

sealed interface MentorUIEffect {
    data object MentorError : MentorUIEffect

    data object NavigateToScheduleMeeting : MentorUIEffect
}
