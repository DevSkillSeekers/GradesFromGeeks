package com.solutionteam.mindfulmentor.ui.search

sealed interface SearchUIEffect {
    object SearchError : SearchUIEffect
}