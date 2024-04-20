package com.rayliu.commonmain.utils

import kotlinx.datetime.LocalDateTime

fun LocalDateTime.toDateString(): String = "${this.year}/${this.monthNumber}/${this.dayOfMonth}"

fun LocalDateTime.toTimeString(): String = "${this.hour}:${this.minute}"
