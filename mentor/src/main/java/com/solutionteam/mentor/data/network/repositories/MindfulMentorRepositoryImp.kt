package com.solutionteam.mentor.data.network.repositories

import com.solutionteam.mentor.data.network.BaseRepository
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mentor.ui.profile.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MindfulMentorRepositoryImp : BaseRepository(), MindfulMentorRepository {


    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }

    //region Language
    //FOR TEST ONLY
    private val appLanguage: MutableStateFlow<Language> = MutableStateFlow(Language.ENGLISH)
    private val appTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun saveLanguage(language: Language) {
        //ToDo: save language inDB
        appLanguage.tryEmit(language)
    }

    override fun getLanguage(): Flow<Language> {
        //ToDo: get saved language in DB
        return appLanguage
    }

    override fun setTheme(isDark: Boolean) {
        //ToDo: get saved Theme in DB
        appTheme.tryEmit(isDark)
    }

    override fun getTheme(): Flow<Boolean> {
        //ToDo: get saved language in DB
        return appTheme
    }
    //endregion

    //region Fake Data
    private fun generatorMentor(): List<Mentor> {
        val list = mutableListOf<Mentor>()
        for (i in 0..10) {
            list.add(
                Mentor(
                    id = "$i",
                    name = "First Last$i",
                    imageUrl = getProfileImage(),
                    rate = (0..10).random().toDouble(),
                    numberReviewers = (1..500).random(),
                )
            )
        }
        return list
    }

    private fun getProfileImage(): String {
        val list = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGuH6Vo5XDGGvgriYJwqI9I8efWEOeVQrVTw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFiWs0Sx8Omxw_qamwrZT_EYTz27HulwjRBA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU"
        )

        return list.shuffled().first()
    }

    private fun generateSubjects(): List<Subject> {
        return listOf(
            Subject(id = "1", name = "Design Pattern"),
            Subject(id = "2", name = "Data Structures"),
            Subject(id = "3", name = "Algorithms"),
            Subject(id = "4", name = "Software Engineering"),
            Subject(id = "5", name = "Database Systems"),
            Subject(id = "6", name = "Web Development"),
            Subject(id = "7", name = "Mobile App Development"),
            Subject(id = "8", name = "Artificial Intelligence"),
            Subject(id = "9", name = "Machine Learning"),
            Subject(id = "10", name = "Computer Networks")
        )
    }

    private fun generateUniversities(): List<University> {
        val list = mutableListOf<University>()
        for (i in 0..10) {
            list.add(
                University(
                    id = "$i",
                    name = "First Last$i",
                    imageUrl = getImage(),
                    address = "Seattle, Washington"
                )
            )
        }
        return list
    }
    private fun generateUniversitiesNames(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..10) {
            list.add(
                "University $i"
            )
        }
        return list
    }
    private fun generateFields(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..10) {
            list.add(
                "Field $i"
            )
        }
        return list
    }
    private fun generateLevels(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 0..10) {
            list.add(
                i
            )
        }
        return list
    }
    private fun getImage(): String {
        val list = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgorKUEVujUWNUHzI_fM_pQX2or-AiH6j29Q&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQANtG6UPPvIwDcLr4wpV4pt3ixtkv8eHjlKg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-Fzwk77TGkO86UCbElFcSkqwx1DcSI_bwQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsxt4TE55zRBBGspJT4FAm_pi1ZfDbLXGPUA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY9goD6bOsmy-JWoW-4u44Dp8tyR2WwpKlSw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq6qXvZ7aaZxvL2diHXHJ47C8J7NDJ2SLXaQ&usqp=CAU",
        )

        return list.shuffled().first()
    }



    private fun getFormattedDate(addDays: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, addDays)

        val dateFormat = SimpleDateFormat("EEE d, MMM yyyy", Locale.US)
        return dateFormat.format(currentDate.time)
    }

    private fun getFormattedTime(addHours: Int): Long {
        val currentTime = Calendar.getInstance()
        currentTime.add(Calendar.HOUR_OF_DAY, addHours)
        return currentTime.timeInMillis
    }

    //endregion

}
