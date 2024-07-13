package com.solutionteam.mentor.ui.videos

import com.solutionteam.mentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel

class VideosViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<VideosUIState, VideosUIEffect>(VideosUIState()) {


}
