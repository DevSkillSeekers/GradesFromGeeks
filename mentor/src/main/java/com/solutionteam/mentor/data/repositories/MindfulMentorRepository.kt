package com.solutionteam.mentor.data.repositories

import com.solutionteam.mentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mentor.ui.profile.Language
import com.solutionteam.mindfulmentor.data.entity.Subject
import kotlinx.coroutines.flow.Flow

interface MindfulMentorRepository {

    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion


    //region Language and Theme
    fun saveLanguage(language: Language)
    fun getLanguage(): Flow<Language>
    fun setTheme(isDark: Boolean)
    fun getTheme(): Flow<Boolean>
    //endregion

    suspend fun getUpComingMeetings(): List<Meeting>


    //region Subject
    suspend fun getSubject(): List<Subject>

    //endregion
}
