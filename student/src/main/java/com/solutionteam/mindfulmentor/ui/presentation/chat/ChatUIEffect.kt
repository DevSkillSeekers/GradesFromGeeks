package com.solutionteam.mindfulmentor.ui.presentation.chat

sealed interface ChatUIEffect {
    object Error : ChatUIEffect
}