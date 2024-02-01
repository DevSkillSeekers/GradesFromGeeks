package com.solutionteam.mindfulmentor.ui.seeAll

import com.solutionteam.mindfulmentor.ui.home.MentorUiState
import com.solutionteam.mindfulmentor.ui.home.UniversityUiState

data class SeeAllUIState(
    val type: SeeAllType = SeeAllType.NoThing,
    val universities: List<UniversityUiState> = emptyList(),
    val mentors: List<MentorUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


enum class SeeAllType(val value: String) {
    Mentors("Mentors"),
    Universities("Universities"),
    NoThing(""),

}

fun String.toSeeAllType(): SeeAllType {
    return when (this) {
        SeeAllType.Mentors.value -> SeeAllType.Mentors
        SeeAllType.Universities.value -> SeeAllType.Universities
        else -> SeeAllType.NoThing
    }
}
