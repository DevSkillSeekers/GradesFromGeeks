package com.solutionteam.mindfulmentor.ui.presentation.chat

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.type.content
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChatBotViewModel(
    private val mentorRepository: MindfulMentorRepository
) : BaseViewModel<ChatUiState, ChatUIEffect>(ChatUiState()) {

    private fun getData(msg: String) {
        val chat = mentorRepository.generateContent(
                userContent = state.value.userRole, modelContent = state.value.modelRole
        )
        val completeText = StringBuilder()
        val messages = state.value.messages.toMutableList()
        messages.add(MessageUIState(isMe = true, message = ""))
        viewModelScope.launch {
            chat.sendMessageStream(msg).map { it.text ?: "" }
                .onEach { completeText.append(it) }
                .onStart { updateState { it.copy(canNotSendMessage = true) } }
                .onCompletion {
                    updateState { it.copy(canNotSendMessage = false) }
                    chat.history.add(content(role = "user") { text(msg) })
                    chat.history.add(content(role = "model") { text(completeText.toString()) })
                }.catch { e ->
                    updateState { it.copy(canNotSendMessage = true) }
                    Log.e("ChatBotViewModel", "getData: ${e.message}")
//                        sendNewEffect(ChatUIEffect.Error(e.message ?: ""))
                }
                .collectLatest { reply ->
                    val old = messages.last().message + " " + reply
                    messages.removeLast()
                    messages.add(MessageUIState(isMe = false, message = old))
                    updateState { it.copy(messages = messages) }
                }
        }
    }


    fun onSendClicked() {
        val oldMsg = state.value.message
        val messages = state.value.messages.toMutableList()
        messages.add(MessageUIState(isMe = true, message = oldMsg))
        updateState { it.copy(messages = messages, message = "") }
        getData(oldMsg)
    }


    fun onChanceMessage(newValue: String) {
        updateState { it.copy(message = newValue) }
    }

}
