package com.solutionteam.mindfulmentor.data.network.response

import com.google.gson.annotations.SerializedName

data class MindfulMentorResponse(
    @SerializedName("user_id")
    val id: String,
)
