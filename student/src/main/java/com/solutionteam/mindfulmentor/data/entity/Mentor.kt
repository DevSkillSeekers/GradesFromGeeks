package com.solutionteam.mindfulmentor.data.entity

data class Mentor(
    val id: String,
    val imageUrl: String,
    val name: String,
    val rate: Double,
    val numberReviewers: Int,
    val summaries: Int,
    val videos: Int,
    val meeting: Int,
    val subjects: List<Subject>,
    val university: String
)
