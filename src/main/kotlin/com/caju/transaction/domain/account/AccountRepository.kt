package com.caju.transaction.domain.account

interface AccountRepository {
    fun findById(id: AccountId): Result<Account>
}