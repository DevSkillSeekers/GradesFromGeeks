package com.solutionteam.mindfulmentor.ui.mentor

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject

data class MentorUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val mentorDetailsUIState: MentorDetailsUIState = MentorDetailsUIState(),
)


data class MentorDetailsUIState(
    val id: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val rate: Double = 0.0,
    val numberReviewers: Int = 0,
    val summaries: Int = 0,
    val videos: Int = 0,
    val meeting: Int = 0,
    val subjects: List<Subject> = emptyList(),
    val university: String = ""
)

data class SummeryDetailsUIState(
    val chapterNumber: String,
    val chapterDescription: String,
    val piedPrice: String,
)

fun Mentor.toUIState(): MentorDetailsUIState {
    return MentorDetailsUIState(
        id = id,
        imageUrl = imageUrl,
        name = name,
        rate = rate,
        numberReviewers = numberReviewers,
        summaries = summaries,
        meeting = meeting,
        subjects = subjects,
        university = university
    )
}