package com.solutionteam.mentor

import android.app.Application
import com.solutionteam.mentor.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class GGApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GGApp)
            modules(appModule())
        }
    }

}
