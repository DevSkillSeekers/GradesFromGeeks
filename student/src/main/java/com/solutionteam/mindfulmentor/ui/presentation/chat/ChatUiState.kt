package com.solutionteam.mindfulmentor.ui.presentation.chat


data class ChatUiState(
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val canNotSendMessage: Boolean = false,
    val error: String? = "",
){
    val userRole: String = "When asked any question that is not related to  , please say: “I don’t know.”"
    val modelRole: String = "Plz ask me about something related on universities just, so I can answer you."
}
data class MessageUIState(
    val isMe: Boolean = false,
    val message: String = "",
)
