package com.solutionteam.mindfulmentor.data.network.repositories

import com.solutionteam.mindfulmentor.data.entity.Mentor

interface MindfulMentorRepository {

    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion

}
