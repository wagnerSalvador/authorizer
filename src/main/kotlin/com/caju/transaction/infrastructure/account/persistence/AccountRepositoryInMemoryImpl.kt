package com.caju.transaction.infrastructure.account.persistence

import com.caju.transaction.domain.account.Account
import com.caju.transaction.domain.account.AccountId
import com.caju.transaction.domain.account.AccountRepository
import com.caju.transaction.infrastructure.mocks.CategoryMock
import jakarta.inject.Singleton

@Singleton
class AccountRepositoryInMemoryImpl : AccountRepository {

    override fun findById(id: AccountId): Result<Account> {
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        return Result.success(Account(accountId, setOf(categoryFood, categoryMeal, categoryCash)))
    }
}