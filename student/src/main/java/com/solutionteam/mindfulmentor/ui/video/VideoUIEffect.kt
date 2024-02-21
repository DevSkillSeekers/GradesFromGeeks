package com.solutionteam.mindfulmentor.ui.video


sealed interface VideoUIEffect {

    data object VideoError : VideoUIEffect

}