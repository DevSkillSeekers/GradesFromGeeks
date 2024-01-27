package com.solutionteam.mindfulmentor.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MINDFUL_MENTOR")
class MindfulMentorEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,

    )