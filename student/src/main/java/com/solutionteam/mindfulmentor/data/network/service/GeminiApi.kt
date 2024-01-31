package com.solutionteam.mindfulmentor.data.network.service

import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

/**
 * Generates a chat content based on the user role and model role.
 *
 * @param userRole The role of the user.
 * @param modelRole The role of the model.
 * @return A Chat object containing the generated content.
 */
class GeminiApi(private val model: GenerativeModel) {

    fun generateContent(userRole: String, modelRole: String): Chat {
        return model.startChat(listOf(
                content(role = "user") { text(userRole) },
                content(role = "model") { text(modelRole) }
        ))
    }

}
