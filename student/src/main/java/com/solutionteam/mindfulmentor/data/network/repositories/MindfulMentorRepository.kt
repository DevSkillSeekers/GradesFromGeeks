package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University

interface MindfulMentorRepository {

    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion


    //region Subject
    suspend fun getSubject(): List<Subject>

    //endregion


    //region Universities
    suspend fun getUniversities(): List<University>

    //endregion

    //region gimmien ai
    fun generateContent(userContent: String, modelContent: String): Chat


}
