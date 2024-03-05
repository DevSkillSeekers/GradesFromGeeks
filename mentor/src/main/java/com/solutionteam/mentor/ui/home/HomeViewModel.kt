package com.solutionteam.mentor.ui.home

import com.solutionteam.mentor.data.entity.Meeting
import com.solutionteam.mentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel
import com.solutionteam.mentor.utils.isLessThanXMinutes
import com.solutionteam.mindfulmentor.data.entity.Subject

class HomeViewModel(
    private val repository: MindfulMentorRepository
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()) {

    init {
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        getUpComingMeetings()
        getSubjects()
    }

    private fun getUpComingMeetings() {
        tryToExecute(
            repository::getUpComingMeetings,
            ::onSuccessMeetings,
            ::onError
        )
    }

    private fun onSuccessMeetings(meetings: List<Meeting>) {
        updateState {
            it.copy(
                upComingMeetings = meetings.take(2).toUpCompingMeetingUiState(), isLoading = false
            )
        }
    }

    private fun getSubjects() {
        tryToExecute(
            repository::getSubject,
            ::onSuccessSubject,
            ::onError
        )
    }

    private fun onSuccessSubject(subjects: List<Subject>) {
        updateState { it.copy(subjects = subjects.take(6).toSubjectUiState(), isLoading = false) }
    }

    private fun onError() {
        updateState { HomeUIState(isError = true, isLoading = false) }
        sendNewEffect(HomeUIEffect.HomeError)
    }

    fun updateMeeting() {
        updateState {
            it.copy(
                upComingMeetings = it.upComingMeetings.mapNotNull { meeting ->
                    val reminder = getReminderTime(meeting.time)
                    if (reminder < -10) {
                        null
                    } else {
                        meeting.copy(
                            reminder = reminder,
                            enableJoin = meeting.time.isLessThanXMinutes()
                        )
                    }
                }
            )
        }
    }

}
