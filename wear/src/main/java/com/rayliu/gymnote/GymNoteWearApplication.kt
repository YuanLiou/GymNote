package com.rayliu.gymnote

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GymNoteWearApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GymNoteWearApplication)
            androidLogger()
            modules(appModule)
        }
    }
}