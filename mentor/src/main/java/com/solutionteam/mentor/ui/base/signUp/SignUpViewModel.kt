package com.solutionteam.mentor.ui.base.signUp

import com.solutionteam.mentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel

class SignUpViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<ProfileUIState, SignUpUIEffect>(ProfileUIState()) {




}
