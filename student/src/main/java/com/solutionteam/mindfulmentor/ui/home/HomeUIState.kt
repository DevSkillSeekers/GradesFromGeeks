package com.solutionteam.mindfulmentor.ui.home

import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.utils.isLessThanXMinutes
import java.util.concurrent.TimeUnit

data class HomeUIState(
    val mentors: List<MentorUiState> = emptyList(),
    val subjects: List<SubjectDetailsUiState> = emptyList(),
    val university: List<UniversityUiState> = emptyList(),
    val upComingMeetings: List<MeetingUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


data class MentorUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val rate: Double = 0.0,
    val numberReviewers: Int = 0
)

data class SubjectDetailsUiState(
    val id: String = "",
    val name: String = ""
)


data class UniversityUiState(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val imageUrl: String = ""
)

data class MeetingUiState(
    val id: String = "",
    val subject: String = "",
    val time: Long = 0L,
    val mentorName: String = "",
    val notes: String = "",
    val reminder: Long = 0L,
    val enableJoin: Boolean = false,
)

fun List<Any>.showSeeAll(): Boolean {
    return this.size > 3
}

//region Mappers

fun Mentor.toUiState() = MentorUiState(
    id = id,
    name = name,
    imageUrl = imageUrl,
    rate = rate,
    numberReviewers = numberReviewers,
)


fun List<Mentor>.toUiState() = map { it.toUiState() }

fun Subject.toSubjectUiState() = SubjectDetailsUiState(id, name)

fun List<Subject>.toSubjectUiState() = map { it.toSubjectUiState() }


fun University.toUniversityUiState() = UniversityUiState(
    id = id,
    imageUrl = imageUrl,
    name = name,
    address = address
)

fun List<University>.toUniversityUiState() = map { it.toUniversityUiState() }


fun Meeting.toUpCompingMeetingUiState() = MeetingUiState(
    id = id,
    time = time,
    subject = subject,
    notes = notes,
    mentorName = mentor.name,
    enableJoin = time.isLessThanXMinutes(),
    reminder = getReminderTime(time)
)

fun List<Meeting>.toUpCompingMeetingUiState() = map { it.toUpCompingMeetingUiState() }


fun getReminderTime(timestamp: Long): Long {
    val currentTimestamp = System.currentTimeMillis()
    val differenceMillis = timestamp - currentTimestamp
    return TimeUnit.MILLISECONDS.toMinutes(differenceMillis)
}
//endregion
