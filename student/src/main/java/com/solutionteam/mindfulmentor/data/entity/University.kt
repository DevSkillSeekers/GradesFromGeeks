package com.solutionteam.mindfulmentor.data.entity

data class University(
    val id: String,
    val name: String,
    val imageUrl: String,
    val address: String,
    val mentorNumber: String,
    val summaryNumber: String,
    val videoNumber: String,
    val subjects: List<Subject>,
    val mentors: List<Mentor>
)
