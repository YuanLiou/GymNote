package com.rayliu.gymnote

import android.app.Application
import com.rayliu.commonmain.domain.repository.RepositoryModule
import com.rayliu.commonmain.domain.usecase.UseCaseModule
import com.rayliu.gymnote.wearos.di.WearModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class GymNoteWearApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GymNoteWearApplication)
            androidLogger()
            modules(
                WearModule().module +
                RepositoryModule().module +
                UseCaseModule().module
            )
        }
    }
}