package com.solutionteam.mindfulmentor.di

import androidx.room.Room
import com.google.gson.Gson
import com.solutionteam.mindfulmentor.data.source.local.Converters
import com.solutionteam.mindfulmentor.data.source.local.database.MindfulMentorDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val LocalDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MindfulMentorDataBase::class.java, "MindfulMentorDatabase")
            .build()
    }

    single {
        val database = get<MindfulMentorDataBase>()
        database.mindfulMentorDao()
    }
}
