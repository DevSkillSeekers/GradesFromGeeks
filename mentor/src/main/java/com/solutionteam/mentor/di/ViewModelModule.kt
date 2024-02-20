package com.solutionteam.mentor.di

import com.solutionteam.mentor.ui.videos.VideosViewModel
import com.solutionteam.mentor.ui.home.HomeViewModel
import com.solutionteam.mentor.ui.profile.ProfileViewModel
import com.solutionteam.mentor.ui.wallet.WalletViewModel
import com.solutionteam.mentor.ui.main.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.solutionteam.mentor.ui.base.signUp.SignUpViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::VideosViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::WalletViewModel)
    viewModelOf(::AppViewModel)
    viewModelOf(::SignUpViewModel)

}
