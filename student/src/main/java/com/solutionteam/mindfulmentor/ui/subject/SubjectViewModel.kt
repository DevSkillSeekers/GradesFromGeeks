package com.solutionteam.mindfulmentor.ui.subject

import android.util.Log
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import com.solutionteam.mindfulmentor.ui.home.toSubjectUiState
import com.solutionteam.mindfulmentor.ui.home.toUiState
import kotlinx.coroutines.delay

class SubjectViewModel(
    val id: String,
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<SubjectUIState, SubjectUIEffect>(SubjectUIState()) {

    init {
        Log.i("lllllllllll", id)
        onMakeRequest()
        getMentors()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
                delay(1000)
                ggRepository.getSubjectById(id)
            },
            ::onSuccess,
            ::onError
        )
    }

    private fun getMentors() {
        tryToExecute(
            {
                ggRepository.getMentors()
            },
            { mentors ->
                updateState { it.copy(subjectMentors = mentors.toUiState()) }
            },
            ::onError
        )
    }

    private fun onSuccess(subject: Subject) {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
                subjectDetails = subject.toSubjectUiState(),
            )
        }
    }

    private fun onError() {
        updateState {
            SubjectUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(SubjectUIEffect.SubjectError)
    }


}
