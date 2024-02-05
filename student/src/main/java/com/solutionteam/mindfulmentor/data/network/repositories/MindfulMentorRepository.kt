package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.SearchResult
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import kotlinx.coroutines.flow.Flow
import com.solutionteam.mindfulmentor.ui.profile.Language

interface MindfulMentorRepository {
    suspend fun getIsFirstTimeUseApp(): Boolean
    suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean)

    suspend fun getSearch(keyword: String,limit:Int): SearchResult

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
