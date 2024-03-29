package com.solutionteam.mindfulmentor.ui.seeAll

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import com.solutionteam.mindfulmentor.ui.home.toUiState
import com.solutionteam.mindfulmentor.ui.home.toUniversityUiState

class SeeAllViewModel(
    private val type: SeeAllType,
    private val repository: MindfulMentorRepository
) : BaseViewModel<SeeAllUIState, SeeAllUIEffect>(SeeAllUIState()) {

    init {
        updateState { it.copy(type = type) }
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        when (state.value.type) {
            SeeAllType.Mentors -> getMentors()
            SeeAllType.Universities -> getUniversities()
            SeeAllType.Subjects -> {}
        }
    }

    private fun getMentors() {
        tryToExecute(repository::getMentors, ::onSuccessMentors, ::onError)
    }

    private fun onSuccessMentors(mentor: List<Mentor>) {
        updateState { it.copy(mentors = mentor.toUiState(), isLoading = false) }
    }

    private fun getUniversities() {
        tryToExecute(repository::getUniversities, ::onSuccessUniversity, ::onError)
    }

    private fun onSuccessUniversity(universities: List<University>) {
        updateState {
            it.copy(universities = universities.toUniversityUiState(), isLoading = false)
        }
    }

    private fun onError() {
        updateState { SeeAllUIState(isError = true, isLoading = false) }
        sendNewEffect(SeeAllUIEffect.SeeAllError)
    }

}
