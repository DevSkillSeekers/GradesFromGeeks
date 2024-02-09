package com.solutionteam.mindfulmentor.ui.chat


data class ChatUiState(
    val messages: List<MessageUIState> = emptyList(),
    val universities: List<String> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val canNotSendMessage: Boolean = false,
    val error: String? = "",
    val userRole: String = "",
    val modelRole: String = "",
    val isFirstEnter: Boolean = true,
    val selectedUniversity: Int = -1,
) {

}

data class MessageUIState(
    val isMe: Boolean = false,
    val message: String = "",
)
