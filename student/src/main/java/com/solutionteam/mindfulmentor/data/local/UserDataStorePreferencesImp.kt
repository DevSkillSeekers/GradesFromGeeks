package com.solutionteam.mindfulmentor.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class UserDataStorePreferencesImp(
    private val userDataStore: DataStore<Preferences>
) : UserPreferences {

    companion object {
        const val PREFERENCES_FILE_NAME = "grades_from_geeks_app"
        private const val START_INSTALL_STATE_KEY = "first_launch_app"
    }
    override suspend fun getIsFirstTimeUseApp(): Boolean? {
        return runBlocking {
            userDataStore.data.map {
                it[booleanPreferencesKey(START_INSTALL_STATE_KEY)]
            }.first()
        }
    }

    override suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean) {
        userDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(START_INSTALL_STATE_KEY)] = isFirstTimeUseApp
        }
    }
}