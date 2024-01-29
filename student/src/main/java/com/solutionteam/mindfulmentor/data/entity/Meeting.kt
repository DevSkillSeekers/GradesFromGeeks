package com.solutionteam.mindfulmentor.data.entity

data class Meeting(
    val id: String,
    val mentor: Mentor,
    val time: Long,
    val subject: String,
    val notes: String,
)
