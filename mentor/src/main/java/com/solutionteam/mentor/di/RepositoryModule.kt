package com.solutionteam.mentor.di

import com.solutionteam.mentor.data.repositories.AuthRepository
import com.solutionteam.mentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mentor.data.repositories.MindfulMentorRepositoryImp
import com.solutionteam.mentor.data.repositories.AuthRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf(::MindfulMentorRepositoryImp) { bind<MindfulMentorRepository>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
}
