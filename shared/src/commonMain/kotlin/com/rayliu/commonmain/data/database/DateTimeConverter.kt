package com.rayliu.commonmain.data.database

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeConverter {
    fun provideCurrentMoment(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun provideNowTimeStamps(): String = toDatabaseDateTime(Clock.System.now())

    fun toDatabaseDateTime(dateTime: Instant): String = dateTime.toString()

    fun toLocalDateTime(timeStamps: String): LocalDateTime = Instant.parse(timeStamps).toLocalDateTime(TimeZone.currentSystemDefault())
}