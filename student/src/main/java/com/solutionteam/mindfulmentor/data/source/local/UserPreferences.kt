package com.solutionteam.mindfulmentor.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.solutionteam.mindfulmentor.ui.profile.Language
import com.solutionteam.mindfulmentor.ui.profile.getLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


interface UserPreferences {
    suspend fun getIsFirstTimeUseApp(): Boolean?
    suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean)

    fun isDarkTheme(): Flow<Boolean?>
    suspend fun saveTheme(isDark: Boolean)

    fun getLanguage(): Flow<Language>
    suspend fun saveLanguage(lang: Language)
}


class UserDataStorePreferencesImp(
    private val userDataStore: DataStore<Preferences>
) : UserPreferences {
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

    override fun isDarkTheme(): Flow<Boolean?> {
        return userDataStore.data.map {
            it[booleanPreferencesKey(PREFERENCES_THEME)]
        }
    }

    override suspend fun saveTheme(isDark: Boolean) {
        userDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(PREFERENCES_THEME)] = isDark
        }
    }

    override fun getLanguage(): Flow<Language> {
        return userDataStore.data.map {
            it[stringPreferencesKey(PREFERENCES_LANGUAGE)].getLanguage()
        }
    }

    override suspend fun saveLanguage(lang: Language) {
        userDataStore.edit { preferences ->
            preferences[stringPreferencesKey(PREFERENCES_LANGUAGE)] = lang.abbreviation
        }
    }

    companion object {
        const val PREFERENCES_FILE_NAME = "grades_from_geeks_app"
        private const val START_INSTALL_STATE_KEY = "first_launch_app"

        private const val PREFERENCES_THEME = "Theme"
        private const val PREFERENCES_LANGUAGE = "Language"
    }
}
