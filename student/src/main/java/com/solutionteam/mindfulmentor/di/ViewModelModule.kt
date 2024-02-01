package com.solutionteam.mindfulmentor.di

import com.solutionteam.mindfulmentor.ui.presentation.auth.login.LoginViewModel
import com.solutionteam.mindfulmentor.ui.presentation.auth.welcome.WelcomeViewModel
import com.solutionteam.mindfulmentor.ui.presentation.downloads.DownloadsViewModel
import com.solutionteam.mindfulmentor.ui.presentation.home.HomeViewModel
import com.solutionteam.mindfulmentor.ui.presentation.search.SearchViewModel
import com.solutionteam.mindfulmentor.ui.presentation.profile.ProfileViewModel
import com.solutionteam.mindfulmentor.ui.presentation.auth.signin.SignInViewModel
import com.solutionteam.mindfulmentor.ui.presentation.seeAll.SeeAllViewModel
import com.solutionteam.mindfulmentor.ui.presentation.mentor.MentorViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::DownloadsViewModel)
    viewModelOf(::SeeAllViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::MentorViewModel)
    viewModelOf(::SignInViewModel)
}
