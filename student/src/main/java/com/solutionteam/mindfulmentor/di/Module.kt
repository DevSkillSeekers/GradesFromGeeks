package com.solutionteam.mindfulmentor.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.solutionteam.mindfulmentor.data.repositories.AuthRepository
import com.solutionteam.mindfulmentor.data.repositories.GoogleAuthUiClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


fun appModule() = module {

    includes(
        AiModel,
        LocalDatabaseModule,
        NetworkModule,
        RepositoryModule,
        viewModelModule,
        DataStoreModule,
        VideoPlayerModule
    )

    single<SignInClient> { Identity.getSignInClient(androidApplication().applicationContext) }

    single<Context> { androidApplication().applicationContext }
    singleOf(::GoogleAuthUiClient) { bind<GoogleAuthUiClient>()}

}
