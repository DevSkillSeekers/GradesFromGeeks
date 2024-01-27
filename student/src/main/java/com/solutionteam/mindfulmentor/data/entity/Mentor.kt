package com.solutionteam.mindfulmentor.data.entity

data class Mentor(
    val id: String,
    val imageUrl: String,
    val name: String,
    val rate: Double,
    val numberReviewers: Int
)
