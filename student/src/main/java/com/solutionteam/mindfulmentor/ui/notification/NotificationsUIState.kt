package com.solutionteam.mindfulmentor.ui.notification

import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.data.entity.Notification


data class NotificationsUIState(
    val notifications: List<NotificationState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)


data class NotificationState(
    val id: Int = 0,
    val mentorId: Int = 0,
    val mentorName: String = "",
    val timeCreated: String = "",
    val type: NotificationType = NotificationType.MEETING_ACCEPTED,
    val viewed: Boolean = false,
    val subjectId: Int = 0,
) {
    val subjectIcon: Int
        get() = when (type) {
            NotificationType.MEETING_ACCEPTED -> R.drawable.ic_meeting
            NotificationType.UPCOMING_MEETING -> R.drawable.ic_meeting
            NotificationType.NEW_SCHEDULE_MEETING_GROUP -> R.drawable.ic_meeting
            NotificationType.NEW_SUMMARY -> R.drawable.ic_summary
            NotificationType.NEW_VIDEO -> R.drawable.ic_video_play
        }

}

enum class NotificationType {
    MEETING_ACCEPTED,
    UPCOMING_MEETING,
    NEW_SCHEDULE_MEETING_GROUP,
    NEW_SUMMARY,
    NEW_VIDEO,

}

fun Notification.toUIState(): NotificationState {
    return NotificationState(
            id = id,
            timeCreated = timeCreated,
            type = type,
            viewed = viewed,
            mentorId = ownerId,
            mentorName = mentorName,
            subjectId = subjectId
    )
}


fun List<Notification>.toUIState() = map { it.toUIState() }