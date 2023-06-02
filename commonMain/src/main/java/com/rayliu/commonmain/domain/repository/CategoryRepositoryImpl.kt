package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.Category

class CategoryRepositoryImpl : CategoryRepository {
    override fun provideBasicCategories(): List<Category> {
        return generateBasicCategories()
    }

    private fun generateBasicCategories(): List<Category> {
        return listOf(
            Category(
                id = 0,
                name = "Shoulders"
            ),
            Category(
                id = 1,
                name = "Triceps"
            ),
            Category(
                id = 2,
                name = "Biceps"
            ),
            Category(
                id = 3,
                name = "Chest"
            ),
            Category(
                id = 4,
                name = "Back"
            ),
            Category(
                id = 5,
                name = "Legs"
            ),
            Category(
                id = 6,
                name = "Abs"
            ),
            Category(
                id = 7,
                name = "Cardio"
            )
        )
    }
}