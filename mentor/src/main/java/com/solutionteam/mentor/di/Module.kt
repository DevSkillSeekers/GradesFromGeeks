package com.solutionteam.mentor.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        NetworkModule,
        RepositoryModule,
        viewModelModule,
    )
}
