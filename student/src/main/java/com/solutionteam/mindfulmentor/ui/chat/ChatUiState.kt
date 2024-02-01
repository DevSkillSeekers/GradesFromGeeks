package com.solutionteam.mindfulmentor.ui.chat


data class ChatUiState(
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val canNotSendMessage: Boolean = false,
    val error: String? = "",
    val userRole: String = "",
    val modelRole: String = ""
) {

}

data class MessageUIState(
    val isMe: Boolean = false,
    val message: String = "",
)
