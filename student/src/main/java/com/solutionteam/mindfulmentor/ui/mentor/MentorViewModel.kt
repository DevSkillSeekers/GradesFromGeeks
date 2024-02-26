package com.solutionteam.mindfulmentor.ui.mentor

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Summaries
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class MentorViewModel(
    private val id:String,
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<MentorUIState, MentorUIEffect>(MentorUIState()) {

    init {
        getDataOfMentor()
    }

    private fun getDataOfMentor() {
        updateState { it.copy(isLoading = true) }
        onGetMentorDetails()
        getSummariesOfMentor()
    }

    private fun onGetMentorDetails() {
        tryToExecute(
            {
                delay(1000)
                ggRepository.getMentorDetails(id)
            },
            ::onGetMentorDetailsSuccess,
            ::onError
        )
    }

    private fun onGetMentorDetailsSuccess(mentor: Mentor) {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
                mentorDetailsUIState = mentor.toUIState()
            )
        }
    }

    private fun getSummariesOfMentor() {
        tryToExecute(
            {
                ggRepository.getSummaries()
            },
            ::onGetSummariseSuccess,
            ::onError
        )
    }

    private fun onGetSummariseSuccess(summaries: List<Summaries>) {
        updateState {
            it.copy(
                mentorSummariseList = summaries.toListUIState()
            )
        }
    }


    private fun onError() {
        updateState {
            MentorUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(MentorUIEffect.MentorError)
    }

}
