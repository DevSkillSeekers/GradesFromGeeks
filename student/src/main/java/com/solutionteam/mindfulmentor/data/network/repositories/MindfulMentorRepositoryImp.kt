package com.solutionteam.mindfulmentor.data.network.repositories

import com.solutionteam.mindfulmentor.data.local.database.MindfulMentorDao
import com.solutionteam.mindfulmentor.data.network.BaseRepository

class MindfulMentorRepositoryImp(
    private val mindfulMentorDao: MindfulMentorDao
): BaseRepository(),MindfulMentorRepository {
}