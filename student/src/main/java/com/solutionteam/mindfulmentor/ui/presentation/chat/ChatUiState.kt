package com.solutionteam.mindfulmentor.ui.presentation.chat


data class ChatUiState(
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val canNotSendMessage: Boolean = false,
    val error: String? = "",
)
data class MessageUIState(
    val isMe: Boolean = false,
    val message: String = "",
)
