package com.solutionteam.mindfulmentor.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.solutionteam.mindfulmentor.data.source.local.UserDataStorePreferencesImp
import com.solutionteam.mindfulmentor.data.source.local.UserPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DataStoreModule = module {

    single {
        PreferenceDataStoreFactory.create(
                produceFile = { androidApplication().preferencesDataStoreFile(
                        UserDataStorePreferencesImp.PREFERENCES_FILE_NAME
                ) }
        )

    }

    singleOf(::UserDataStorePreferencesImp) { bind<UserPreferences>() }
}
