package com.solutionteam.mindfulmentor.data.local


interface UserPreferences {
    suspend fun getIsFirstTimeUseApp(): Boolean?
    suspend fun saveIsFirstTimeUseApp(isFirstTimeUseApp: Boolean)

}