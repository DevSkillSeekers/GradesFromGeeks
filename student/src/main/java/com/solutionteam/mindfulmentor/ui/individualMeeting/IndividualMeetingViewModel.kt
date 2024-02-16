package com.solutionteam.mindfulmentor.ui.individualMeeting

import com.solutionteam.mindfulmentor.data.entity.Date
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import com.solutionteam.mindfulmentor.ui.mentor.MentorUIEffect
import com.solutionteam.mindfulmentor.ui.mentor.MentorUIState
import kotlinx.coroutines.delay

class IndividualMeetingViewModel(
    private val repository: MindfulMentorRepository
) : BaseViewModel<IndividualMeetingUIState, IndividualMeetingUIEffect>(IndividualMeetingUIState()) {

    init {
        getData()
    }

    private fun getData() {
        tryToExecute(
            {
                updateState { it.copy(isLoading = true, isError = false) }
                delay(1000)
                repository.getAvailableTimeForMentor("")
            },
            ::onSuccess, ::onError
        )
    }

    private fun onSuccess(dates: List<Date>) {
        updateState { it.copy(isLoading = false, availableDates = dates.toDateUiState()) }
    }

    private fun onError() {
        updateState {
            IndividualMeetingUIState(isError = true, isLoading = false)
        }
        sendNewEffect(IndividualMeetingUIEffect.IndividualMeetingError)
    }


    fun onTimeSelected(time: TimeUiState) {
        updateState {
            it.copy(
                selectedTime = time,
                showBottomSheet = true,
                availableDates = it.availableDates.map { it.setSelectTime(time.id) }
            )
        }
    }

    fun onDismissRequest() {
        updateState {
            it.copy(
                selectedTime = null,
                showBottomSheet = false,
                availableDates = it.availableDates.map { it.setSelectTime() })
        }
    }

    fun onValueChange(newNote: String) {
        updateState { it.copy(note = newNote) }
    }

    fun onBookClick() {
        updateState {
            it.copy(
                selectedTime = null,
                showBottomSheet = false,
                availableDates = it.availableDates.map { it.setSelectTime() }
            )
        }
    }

}
