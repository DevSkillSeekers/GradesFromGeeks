package com.solutionteam.mentor.ui.videos

sealed interface VideosUIEffect {
    data object VideosError : VideosUIEffect
}
