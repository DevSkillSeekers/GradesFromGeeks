package com.solutionteam.mindfulmentor.data.repositories

import com.google.ai.client.generativeai.Chat
import com.solutionteam.mindfulmentor.data.entity.Date
import com.solutionteam.mindfulmentor.data.entity.Meeting
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.entity.Notification
import com.solutionteam.mindfulmentor.data.entity.SearchResult
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.data.entity.Summaries
import com.solutionteam.mindfulmentor.data.entity.University
import com.solutionteam.mindfulmentor.data.source.BaseRepository
import com.solutionteam.mindfulmentor.data.source.local.UserPreferences
import com.solutionteam.mindfulmentor.data.source.local.database.MindfulMentorDao
import com.solutionteam.mindfulmentor.data.source.remote.service.GeminiApi
import com.solutionteam.mindfulmentor.ui.notification.NotificationType
import com.solutionteam.mindfulmentor.ui.profile.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MindfulMentorRepositoryImp(
    private val mindfulMentorDao: MindfulMentorDao,
    private val geminiApi: GeminiApi,
    private val authorizationPreferences: UserPreferences
) : BaseRepository(), MindfulMentorRepository {

    override fun generateContent(userContent: String, modelContent: String): Chat {
        return geminiApi.generateContent(userRole = userContent, modelRole = modelContent)
    }

    override suspend fun getUniversitiesName(): List<String> {
        return listOf(
                "University of Washington",
                "University of California, Los Angeles",
                "University of Baghdad",
                "University of California, Berkeley",
                "Harvard University",
                "Stanford University",
                "Massachusetts Institute of Technology (MIT)",
                "University of Oxford",
                "University of Cambridge",
                "California Institute of Technology (Caltech)",
                "ETH Zurich - Swiss Federal Institute of Technology",
                "University College London (UCL)",
                "University of Chicago",
                "Imperial College London"
        )
    }


    override suspend fun getIsFirstTimeUseApp(): Boolean {
        return authorizationPreferences.getIsFirstTimeUseApp() ?: true
    }

    override suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean) {
        return authorizationPreferences.saveIsFirstTimeUseApp(isFirstTimeUseApp)
    }

    override suspend fun getSearch(keyword: String,limit:Int): SearchResult {
        return  if (keyword.isNotEmpty()) {
            val result = SearchResult(
                    universities = getUniversities().filter { it.name.contains(keyword, ignoreCase = true) },
                    mentors = getMentors().filter { it.name.contains(keyword, ignoreCase = true) },
                    subject = getSubject().filter { it.name.contains(keyword, ignoreCase = true) }
            )
             result
        } else {
            SearchResult()
        }
    }

    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }

    override suspend fun getMentorDetails(id: String): Mentor {
        return generatorMentor().find { it.id == id } ?: throw Exception("empty details")
    }

    override suspend fun getSummaries(): List<Summaries> {
        return listOf(
            Summaries(
                chapterNumber = "Chapter 1",
                chapterDescription = "15 page (pdf)",
                piedPrice = ""
            ),
            Summaries(
                chapterNumber = "Chapter 2",
                chapterDescription = "15 page (pdf)",
                piedPrice = ""
            ),
            Summaries(
                chapterNumber = "Chapter 3",
                chapterDescription = "15 page (pdf)",
                piedPrice = "10$"
            ),
            Summaries(
                chapterNumber = "Chapter 4",
                chapterDescription = "15 page (pdf)",
                piedPrice = ""
            ),
            Summaries(
                chapterNumber = "Chapter 5",
                chapterDescription = "15 page (pdf)",
                piedPrice = "5$"
            ),
        )
    }

    override suspend fun getVideos(): List<Summaries> {
        return listOf(
            Summaries(
                chapterNumber = "Chapter 1",
                chapterDescription = "15 page (pdf)",
                piedPrice = "10$"
            ),
            Summaries(
                chapterNumber = "Chapter 5",
                chapterDescription = "11 page (pdf)",
                piedPrice = "5$"
            ),
            Summaries(
                chapterNumber = "Chapter 11",
                chapterDescription = "3 page (pdf)",
                piedPrice = ""
            )
        )
    }

    override suspend fun getMeeting(): List<Meeting> {
        return generateMeeting()
    }

    override suspend fun getSubject(): List<Subject> {
        return generateSubjects()
    }

    override suspend fun getNotifications(): List<Notification> {
        return listOf(
            Notification(
                id = 1,
                ownerId = 3,
                        mentorName = "Nada Feteiha",
                        timeCreated = "12 sec",
                        type = NotificationType.MEETING_ACCEPTED,
                        viewed = true,
                        subjectId = 1,
                ),
                Notification(
                        id = 2,
                        ownerId = 3,
                        mentorName = "Nada Feteiha",
                        timeCreated = "1 hr ago",
                        type = NotificationType.UPCOMING_MEETING,
                        viewed = false,
                        subjectId = 2
                ),
                Notification(
                        id = 3,
                        ownerId = 3,
                        mentorName = "Nada Feteiha",
                        timeCreated = "1 hr ago",
                        type = NotificationType.NEW_SCHEDULE_MEETING_GROUP,
                        viewed = false,
                        subjectId = 3
                ),
                Notification(
                        id = 4,
                        ownerId = 5,
                        mentorName = "Amnah.a",
                        timeCreated = "56 min ago",
                        type = NotificationType.NEW_SUMMARY,
                        viewed = false,
                        subjectId = 4
                ),
                Notification(
                        id = 5,
                        ownerId = 5,
                        mentorName = "Amnah.a",
                        timeCreated = "56 sec",
                        type = NotificationType.NEW_VIDEO,
                        viewed = true,
                        subjectId = 5
                ),
                Notification(
                        id = 6,
                        ownerId = 5,
                        mentorName = "Amnah.a",
                        timeCreated = "56 sec",
                        type = NotificationType.NEW_VIDEO,
                        viewed = true,
                        subjectId = 5
                ),
                Notification(
                        id = 7,
                        ownerId = 5,
                        mentorName = "Amnah.a",
                        timeCreated = "56 sec",
                        type = NotificationType.NEW_VIDEO,
                        viewed = true,
                        subjectId = 5
                ),
                Notification(
                        id = 8,
                        ownerId = 3,
                        mentorName = "Nada Feteiha",
                        timeCreated = "1 hr ago",
                        type = NotificationType.UPCOMING_MEETING,
                        viewed = false,
                        subjectId = 2
                ),
                Notification(
                        id = 9,
                        ownerId = 3,
                        mentorName = "Nada Feteiha",
                        timeCreated = "1 hr ago",
                        type = NotificationType.NEW_SCHEDULE_MEETING_GROUP,
                        viewed = false,
                        subjectId = 3
                ),
        )
    }

    override suspend fun getUniversities(): List<University> {
        return generateUniversities()
    }

    override  fun getUniversitiesNames(): List<String> {
        return generateUniversitiesNames()
    }

    override suspend fun getUpComingMeetings(): List<Meeting> {
        return generateMeeting()
    }


    //region Language
    //FOR TEST ONLY
    private val appLanguage: MutableStateFlow<Language> = MutableStateFlow(Language.ENGLISH)
    private val appTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override suspend fun saveLanguage(language: Language) {
        authorizationPreferences.saveLanguage(language)
    }

    override fun getLanguage(): Flow<Language> {
        return authorizationPreferences.getLanguage()

    }

    override suspend fun setTheme(isDark: Boolean) {
        authorizationPreferences.saveTheme(isDark)
    }

    override fun getTheme(): Flow<Boolean?> {
        return authorizationPreferences.isDarkTheme()
    }
    //endregion

    override fun getAvailableTimeForMentor(mentorId: String): List<Date> {
        return getDates()
    }

    override suspend fun getFields(): List<String> {
        return generateFields()
    }

    override suspend fun getLevels(): List<Int> {
        return generateLevels()
    }

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
                    summaries = 20 + i,
                    videos = 5 + i,
                    meeting = 19 +i,
                    subjects = generateSubjects(),
                    university = "University $i"
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
                    notes = "This meet to recap data structure from ch$i to ch ${i + 3}",
                    time = System.currentTimeMillis() + i * (30 * 60 * 1000),
                    isBook = i % 2 == 0,
                    price = if (i % 2 == 0) "" else "1$i"
                )
            )
        }
        return meetings
    }

    private fun getDates(): List<Date> {
        val list = mutableListOf<Date>()
        for (i in 0..10) {
            val day = getFormattedDate(addDays = i)
            val times = mutableListOf<Long>()
            for (j in 0..20 step 2) {
                times.add(getFormattedTime(j))
            }
            list.add(Date(day = day, times = times))
        }
        return list
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
