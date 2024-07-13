package com.solutionteam.mentor.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MindfulMentorResponse(
    @SerializedName("user_id")
    val id: String,
)
