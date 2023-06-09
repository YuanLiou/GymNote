package com.rayliu.gymnote

import com.rayliu.commonmain.data.database.androidDatabaseModule
import com.rayliu.commonmain.di.androidDispatcherModule
import com.rayliu.commonmain.domain.repository.RepositoryModule
import com.rayliu.commonmain.domain.usecase.UseCaseModule
import com.rayliu.gymnote.wearos.di.WearModule
import org.koin.dsl.module
import org.koin.ksp.generated.module

internal val appModule = module {
    includes(
        androidDispatcherModule +
            WearModule().module +
            RepositoryModule().module +
            UseCaseModule().module +
            androidDatabaseModule
    )
}