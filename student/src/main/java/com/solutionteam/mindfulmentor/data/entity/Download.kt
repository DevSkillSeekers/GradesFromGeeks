package com.solutionteam.mindfulmentor.data.entity

data class Download(
    val summariesNumber: String,
    val videoNumber: String,
    val meetingNumber: String,
    val subjects: List<Subject>,
    val summaries: List<Summaries>,
    val video: List<Summaries>,
    val meeting: List<Meeting>
)
