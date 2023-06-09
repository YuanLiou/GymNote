package com.rayliu.commonmain.domain.repository

interface WorkoutInfoRepository {
    suspend fun updateInitialDateIfNeeded(initialDate: String)
}