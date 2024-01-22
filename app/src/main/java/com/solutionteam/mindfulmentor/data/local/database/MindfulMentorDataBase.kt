package com.solutionteam.mindfulmentor.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.solutionteam.mindfulmentor.data.local.Converters

@Database(entities = [MindfulMentorEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MindfulMentorDataBase : RoomDatabase() {

    abstract fun mindfulMentorDao(): MindfulMentorDao
}