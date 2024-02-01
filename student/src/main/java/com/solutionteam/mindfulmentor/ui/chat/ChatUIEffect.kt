package com.solutionteam.mindfulmentor.ui.chat

sealed interface ChatUIEffect {
    object Error : ChatUIEffect
}
