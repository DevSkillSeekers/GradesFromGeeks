package com.solutionteam.mindfulmentor.ui.presentation.search

sealed interface SearchUIEffect {
    object SearchError : SearchUIEffect
}