package com.rayliu.commonmain.data.database

import app.cash.sqldelight.db.SqlDriver
import com.rayliu.commonmain.data.dao.DaoModule
import com.rayliu.commonmain.data.mapper.DataMapperModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.ksp.generated.module

val androidDatabaseModule =
    module {
        single<SqlDriver> {
            DatabaseDriverFactory(androidContext()).createDriver()
        }
        includes(
            DatabaseModule().module,
            DaoModule().module,
            DataMapperModule().module
        )
    }