package com.solutionteam.mindfulmentor.ui.notification

import com.solutionteam.mindfulmentor.data.entity.Notification
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import com.solutionteam.mindfulmentor.ui.mentor.MentorUIEffect
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class NotificationsViewModel @Inject constructor(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<NotificationsUIState, MentorUIEffect>(NotificationsUIState()) {

    init {
        getNotificationDetails()
    }

    private fun getNotificationDetails() {
        _state.update { it.copy(isLoading = true, error = "") }
        tryToExecute(
                { mindfulMentorRepository.getNotifications() },
                ::onSuccess,
                ::onError
        )
    }

    private fun onError() {

        _state.update {
            it.copy(
                    isLoading = false,
                    error = if (_state.value.notifications.isEmpty()) "connection error" else ""
            )
        }
    }

    private fun onSuccess(notifications: List<Notification>) {
        _state.update {
            it.copy(
                    notifications = (it.notifications + notifications.toUIState()),
                    isLoading = false,
            )
        }
    }

}