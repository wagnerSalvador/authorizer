package com.caju.transaction.infrastructure.category.persistence

import com.caju.transaction.domain.category.Category
import com.caju.transaction.domain.category.CategoryRepository
import jakarta.inject.Singleton

@Singleton
class CategoryPersistencyInMemoryImpl : CategoryRepository {
    override fun save(updatedCategory: Category) {
        TODO("Not yet implemented")
    }
}