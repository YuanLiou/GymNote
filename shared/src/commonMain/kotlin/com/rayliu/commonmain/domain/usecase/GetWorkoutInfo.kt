package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.WorkoutInfo

fun interface GetWorkoutInfo : suspend (Int) -> WorkoutInfo?