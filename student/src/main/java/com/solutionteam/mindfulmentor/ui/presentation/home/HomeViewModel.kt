package com.solutionteam.mindfulmentor.ui.presentation.home

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel

class HomeViewModel(
    private val repository: MindfulMentorRepository
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()) {

    init {
        getMentors()
    }

    private fun getMentors() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            repository::getMentors,
            ::onSuccessMentors,
            ::onError
        )
    }


    private fun onSuccessMentors(mentor: List<Mentor>) {
        updateState {
            it.copy(
                mentors = mentor.toUiState(),
                isSuccess = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onError() {
        updateState {
            HomeUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(HomeUIEffect.HomeError)
    }


}
