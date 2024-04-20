package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.database.Category
import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.data.mapper.basic.NullableInputListMapper
import com.rayliu.commonmain.domain.model.SportCategory
import org.koin.core.annotation.Factory

@Factory
class CategoryMapper : Mapper<Category, SportCategory> {
    override fun map(input: Category): SportCategory =
        SportCategory(
            id = input.id.toInt(),
            name = input.name
        )
}

@Factory
class CategoryListMapper(
    private val mapper: CategoryMapper
) : NullableInputListMapper<Category, SportCategory> {
    override fun map(input: List<Category>?): List<SportCategory> = input?.map { mapper.map(it) }.orEmpty()
}
