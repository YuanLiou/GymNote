package com.rayliu.commonmain.utils

import kotlinx.datetime.LocalDateTime

fun LocalDateTime.toDateString(): String {
    return "${this.year}/${this.monthNumber}/${this.dayOfMonth}"
}

fun LocalDateTime.toTimeString(): String {
    return "${this.hour}:${this.minute}"
}
