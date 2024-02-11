package com.solutionteam.mindfulmentor.ui.review


sealed interface VideoUIEffect {

    data object VideoError : VideoUIEffect

}