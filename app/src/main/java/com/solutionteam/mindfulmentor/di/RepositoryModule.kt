package com.solutionteam.mindfulmentor.di

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf(::MindfulMentorRepositoryImp) { bind<MindfulMentorRepository>() }
}