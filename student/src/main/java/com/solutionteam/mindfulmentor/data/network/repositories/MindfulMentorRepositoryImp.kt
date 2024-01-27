package com.solutionteam.mindfulmentor.data.network.repositories

import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.data.local.database.MindfulMentorDao
import com.solutionteam.mindfulmentor.data.network.BaseRepository

class MindfulMentorRepositoryImp(
    private val mindfulMentorDao: MindfulMentorDao
) : BaseRepository(), MindfulMentorRepository {

    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }


    //region Fake Data

    private fun generatorMentor(): List<Mentor> {
        val list = mutableListOf<Mentor>()
        for (i in 0..10) {
            list.add(
                Mentor(
                    id = "$i",
                    name = "First Last$i",
                    imageUrl = getImage(),
                    rate = (0..10).random().toDouble(),
                    numberReviewers = (1..500).random(),
                )
            )
        }
        return list
    }


    private fun getImage(): String {
        val list = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGuH6Vo5XDGGvgriYJwqI9I8efWEOeVQrVTw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFiWs0Sx8Omxw_qamwrZT_EYTz27HulwjRBA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU"
        )

        return list.shuffled().first()
    }

    //endregion
}
