package com.rayliu.commonmain.data.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseModule {
    @Single
    fun provideAppDatabase(sqlDriver: SqlDriver): AppDatabase = AppDatabase(sqlDriver)

    @Factory
    fun provideDateTimeConverter(): DateTimeConverter = DateTimeConverter()
}