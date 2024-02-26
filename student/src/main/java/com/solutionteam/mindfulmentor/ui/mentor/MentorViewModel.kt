package com.solutionteam.mindfulmentor.ui.mentor

import android.util.Log
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class MentorViewModel(
    private val id:String,
    private val ggRepository: MindfulMentorRepository
) : BaseViewModel<MentorUIState, MentorUIEffect>(MentorUIState()) {

    init {
        Log.i("llllllllllll", id)
        getDataOfMentor()
    }

    private fun getDataOfMentor() {
        updateState { it.copy(isLoading = true) }
        onGetMentorDetails()
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
