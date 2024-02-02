package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.ui.profile.Language
import kotlinx.coroutines.flow.Flow

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


    suspend fun getUpComingMeetings(): List<Meeting>

    //endregion

    //region gimmien ai
    fun generateContent(userContent: String, modelContent: String): Chat


    //region Language and Theme
    fun saveLanguage(language: Language)
    fun getLanguage(): Flow<Language>
    fun setTheme(isDark: Boolean)
    fun getTheme(): Flow<Boolean>
    //endregion

}
