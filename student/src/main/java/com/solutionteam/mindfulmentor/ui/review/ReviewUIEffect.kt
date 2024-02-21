package com.solutionteam.mindfulmentor.ui.review


sealed interface ReviewUIEffect {

    data object ReviewError : ReviewUIEffect

}