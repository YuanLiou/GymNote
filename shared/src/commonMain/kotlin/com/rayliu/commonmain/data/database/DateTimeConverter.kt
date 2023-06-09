package com.rayliu.commonmain.data.database

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeConverter {

    fun provideNowTimeStamps(): String {
        return toDatabaseDateTime(Clock.System.now())
    }

    fun toDatabaseDateTime(dateTime: Instant): String {
        return dateTime.toString()
    }

    fun toLocalDateTime(timeStamps: String): LocalDateTime {
        return Instant.parse(timeStamps).toLocalDateTime(TimeZone.currentSystemDefault())
    }
}