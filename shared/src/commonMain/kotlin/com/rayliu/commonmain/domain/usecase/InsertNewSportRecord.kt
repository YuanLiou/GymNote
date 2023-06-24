package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record

fun interface InsertNewSportRecord : suspend (Record) -> Unit