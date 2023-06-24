package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.repository.CategoryRepository
import com.rayliu.commonmain.domain.repository.SportRecordRepository
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class UseCaseModule {

    @Factory
    fun provideGetSportCategory(categoryRepository: CategoryRepository) =
        GetSportCategory(categoryRepository::provideBasicCategories)

    @Factory
    fun provideGetWorkoutInfo(workoutInfoRepository: WorkoutInfoRepository) =
        GetWorkoutInfo(workoutInfoRepository::getWorkoutInfo)

    @Factory
    fun provideInsertNewSportRecord(sportRecordRepository: SportRecordRepository) =
        InsertNewSportRecord(sportRecordRepository::insertNewRecord)
}