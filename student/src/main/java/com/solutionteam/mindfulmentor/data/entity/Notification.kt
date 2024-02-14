package com.solutionteam.mindfulmentor.data.entity

import com.solutionteam.mindfulmentor.ui.notification.NotificationType

data class Notification(
    val id: Int,
    val ownerId: Int,
    val subjectId: Int,
    val timeCreated: String,
    val type: NotificationType,
    val viewed: Boolean = false,
    val mentorName: String,
)
