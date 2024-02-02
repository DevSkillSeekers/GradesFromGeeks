package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.data.local.database.MindfulMentorDao
import com.solutionteam.mindfulmentor.data.network.BaseRepository
import com.solutionteam.mindfulmentor.data.network.service.GeminiApi
import com.solutionteam.mindfulmentor.ui.profile.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MindfulMentorRepositoryImp(
    private val mindfulMentorDao: MindfulMentorDao,
    private val geminiApi: GeminiApi
) : BaseRepository(), MindfulMentorRepository {

    override fun generateContent(userContent: String, modelContent: String): Chat {
        return geminiApi.generateContent(userRole = userContent, modelRole = modelContent)
    }

    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }

    override suspend fun getSubject(): List<Subject> {
        return generateSubjects()
    }

    override suspend fun getUniversities(): List<University> {
        return generateUniversities()
    }

    override suspend fun getUpComingMeetings(): List<Meeting> {
        return generateMeeting()
    }


    //region Language
    //FOR TEST ONLY
    private val appLanguage: MutableStateFlow<Language> = MutableStateFlow(Language.ENGLISH)
    override fun saveLanguage(language: Language) {
        //ToDo: save language inDB
        appLanguage.tryEmit(language)
    }

    override fun getLanguage(): Flow<Language> {
        //ToDo: get saved language in DB
        return appLanguage
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


    private fun generateMeeting(): List<Meeting> {
        val meetings = mutableListOf<Meeting>()
        val mentors = generatorMentor()
        val subject = generateSubjects()
        for (i in 0..5) {
            val mentorIndex = (0..mentors.lastIndex).random()
            val subjectIndex = (0..subject.lastIndex).random()

            meetings.add(
                Meeting(
                    id = "$i",
                    mentor = mentors[mentorIndex],
                    subject = subject[subjectIndex].name,
                    notes = "This meet to recap data structure from ch2 to ch 5",
                    time = System.currentTimeMillis() + i * (30 * 60 * 1000)
                )
            )
        }
        return meetings
    }
    //endregion
}
