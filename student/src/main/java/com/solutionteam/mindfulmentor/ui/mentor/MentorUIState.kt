package com.solutionteam.mindfulmentor.ui.mentor

import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.Summaries

data class MentorUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val mentorDetailsUIState: MentorDetailsUIState = MentorDetailsUIState(),
    val mentorSummariseList: List<SummeryDetailsUIState> = emptyList(),
    val mentorVideoList: List<SummeryDetailsUIState> = emptyList(),
    val mentorMeetingList: List<MeetingUIState> = emptyList()
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
    val chapterNumber: String = "",
    val chapterDescription: String = "",
    val piedPrice: String = "",
    val isBuy: Boolean = false
)

data class MeetingUIState(
    val id: String = "",
    val mentor: MentorDetailsUIState = MentorDetailsUIState(),
    val time: Long = 0L,
    val subject: String = "",
    val notes: String = "",
    val isBook: Boolean = false,
    val price: String = ""
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
        university = university,
        videos = videos
    )
}

fun List<Summaries>.toListUIState() = map { it.toUIState() }

fun Summaries.toUIState(): SummeryDetailsUIState {
    return SummeryDetailsUIState(
        chapterNumber = chapterNumber,
        chapterDescription = chapterDescription,
        piedPrice = piedPrice,
        isBuy = isBuy
    )
}

fun List<Meeting>.toMeetingListUIState() = map { it.toUIState() }

fun Meeting.toUIState(): MeetingUIState {
    return MeetingUIState(
        id = id,
        mentor = mentor.toUIState(),
        subject = subject,
        time = time,
        notes = notes,
        isBook = isBook,
        price = price
    )
}