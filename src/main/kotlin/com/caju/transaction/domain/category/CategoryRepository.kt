package com.caju.transaction.domain.category

interface CategoryRepository {
    fun save(updatedCategory: Category)
}
