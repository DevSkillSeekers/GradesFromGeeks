package com.solutionteam.mindfulmentor.ui.presentation.home

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.presentation.base.BaseViewModel

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


    private fun onError() {
        updateState {
            HomeUIState(
                isError = true,
                isLoading = false,
            )
        }
        sendNewEffect(HomeUIEffect.HomeError)
    }


}
