package com.solutionteam.mentor.ui.home

import com.solutionteam.mentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel

class HomeViewModel(
    private val repository: MindfulMentorRepository
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()) {

    init {
        getData()
    }

    private fun getData() {

    }

}
