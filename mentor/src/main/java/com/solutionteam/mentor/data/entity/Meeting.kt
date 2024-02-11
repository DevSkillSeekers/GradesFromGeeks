package com.solutionteam.mentor.data.entity

data class Meeting(
    val id: String,
    val time: Long,
    val subject: String,
    val notes: String,
    val type: MeetingType
)

enum class MeetingType {
    GROUP,
    INDIVIDUAL
}
