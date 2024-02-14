package com.solutionteam.mindfulmentor.di

import com.solutionteam.mindfulmentor.ui.auth.login.LoginViewModel
import com.solutionteam.mindfulmentor.ui.auth.welcome.WelcomeViewModel
import com.solutionteam.mindfulmentor.ui.downloads.DownloadsViewModel
import com.solutionteam.mindfulmentor.ui.home.HomeViewModel
import com.solutionteam.mindfulmentor.ui.search.SearchViewModel
import com.solutionteam.mindfulmentor.ui.profile.ProfileViewModel
import com.solutionteam.mindfulmentor.ui.auth.signin.SignInViewModel
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllViewModel
import com.solutionteam.mindfulmentor.ui.mentor.MentorViewModel
import com.solutionteam.mindfulmentor.ui.chat.ChatBotViewModel
import com.solutionteam.mindfulmentor.ui.main.AppViewModel
import com.solutionteam.mindfulmentor.ui.university.UniversityViewModel
import com.solutionteam.mindfulmentor.ui.subject.SubjectViewModel
import com.solutionteam.mindfulmentor.ui.individualMeeting.IndividualMeetingViewModel
import com.solutionteam.mindfulmentor.ui.notification.NotificationsViewModel
import com.solutionteam.mindfulmentor.ui.review.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    viewModelOf(::ChatBotViewModel)
    viewModelOf(::AppViewModel)
    viewModelOf(::UniversityViewModel)
    viewModelOf(::SubjectViewModel)
    viewModelOf(::IndividualMeetingViewModel)
    viewModelOf(::VideoViewModel)
    viewModelOf(::NotificationsViewModel)
}
