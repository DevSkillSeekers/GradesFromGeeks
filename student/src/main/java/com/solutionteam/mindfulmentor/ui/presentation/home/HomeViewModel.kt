package com.solutionteam.mindfulmentor.ui.presentation.home

import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel
import com.solutionteam.mindfulmentor.utils.isLessThanXMinutes

class HomeViewModel(
    private val repository: MindfulMentorRepository
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()) {

    init {
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        getMentors()
        getSubjects()
        getUniversities()
        getUpComingMeetings()
    }

    private fun getMentors() {
        tryToExecute(
            repository::getMentors,
            ::onSuccessMentors,
            ::onError
        )
    }

    private fun onSuccessMentors(mentor: List<Mentor>) {
        updateState { it.copy(mentors = mentor.toUiState(), isLoading = false) }
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

    private fun getUniversities() {
        tryToExecute(
            repository::getUniversities,
            ::onSuccessUniversity,
            ::onError
        )
    }

    private fun onSuccessUniversity(universities: List<University>) {
        updateState {
            it.copy(
                university = universities.take(6).toUniversityUiState(), isLoading = false
            )
        }
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
