package com.solutionteam.mentor.ui.home

import com.solutionteam.mentor.data.entity.Meeting
import com.solutionteam.mentor.data.entity.MeetingType
import com.solutionteam.mentor.utils.isLessThanXMinutes
import com.solutionteam.mindfulmentor.data.entity.Subject
import java.util.concurrent.TimeUnit

data class HomeUIState(
    val upComingMeetings: List<MeetingUiState> = emptyList(),
    val subjects: List<SubjectUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

data class MeetingUiState(
    val id: String = "",
    val subject: String = "",
    val type: MeetingType = MeetingType.GROUP,
    val time: Long = 0L,
    val notes: String = "",
    val reminder: Long = 0L,
    val enableJoin: Boolean = false,
)

data class SubjectUiState(
    val id: String = "",
    val name: String = ""
)


//region Mappers

fun List<Any>.showSeeAll(): Boolean {
    return this.size > 3
}
fun Subject.toSubjectUiState() = SubjectUiState(id, name)

fun List<Subject>.toSubjectUiState() = map { it.toSubjectUiState() }


fun Meeting.toUpCompingMeetingUiState() = MeetingUiState(
    id = id,
    time = time,
    type = type,
    subject = subject,
    notes = notes,
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
