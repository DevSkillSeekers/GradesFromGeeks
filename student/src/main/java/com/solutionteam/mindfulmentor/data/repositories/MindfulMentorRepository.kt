package com.solutionteam.mindfulmentor.data.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Date
import com.solutionteam.mindfulmentor.data.entity.Download
import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Notification
import com.solutionteam.mindfulmentor.data.entity.SearchResult
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.Summaries
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import kotlinx.coroutines.flow.Flow
import com.solutionteam.mindfulmentor.ui.profile.Language

interface MindfulMentorRepository {
    suspend fun getVideoUrl(): String
    suspend fun getUniversitiesName(): List<String>
    suspend fun getIsFirstTimeUseApp(): Boolean
    suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean)

    suspend fun getSearch(keyword: String, limit: Int): SearchResult

    //region Download
    suspend fun getDownloadDetails(id: String): Download

    //endregion

    //region Mentor
    suspend fun getMentors(): List<Mentor>
    suspend fun getMentorDetails(id: String): Mentor
    suspend fun getSummaries(): List<Summaries>
    suspend fun getVideos() : List<Summaries>

    suspend fun getMeeting(): List<Meeting>

    //endregion


    //region Subject
    suspend fun getSubject(): List<Subject>
    suspend fun getSubjectById(id: String) : Subject

    //endregion

    //region Notifications
    suspend fun getNotifications(): List<Notification>
    //endregion


    //region Universities
    suspend fun getUniversities(): List<University>
    fun getUniversitiesNames(): List<String>

    suspend fun getUniversityById(id: String): University


    //endregion


    suspend fun getUpComingMeetings(): List<Meeting>

    //endregion

    //region gimmien ai
    fun generateContent(userContent: String, modelContent: String): Chat


    //region Language and Theme
    suspend fun saveLanguage(language: Language)
    fun getLanguage(): Flow<Language>
    suspend fun setTheme(isDark: Boolean)
    fun getTheme(): Flow<Boolean?>
    //endregion

    fun getAvailableTimeForMentor(mentorId: String): List<Date>

    // region fields
    suspend fun getFields(): List<String>
    //endregion

    //region Levels
    suspend fun getLevels(): List<Int>
    //endregion

}
