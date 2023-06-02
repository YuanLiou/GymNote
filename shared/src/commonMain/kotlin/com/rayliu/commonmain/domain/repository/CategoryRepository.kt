package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.Category

interface CategoryRepository {
    fun provideBasicCategories(): List<Category>
}