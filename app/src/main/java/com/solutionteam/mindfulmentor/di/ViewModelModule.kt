package com.solutionteam.mindfulmentor.di

import com.solutionteam.mindfulmentor.ui.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}