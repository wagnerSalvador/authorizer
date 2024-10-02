package com.caju.transaction.domain.account

import com.caju.transaction.domain.category.Category
import com.caju.transaction.domain.exceptions.InsufficientFounds
import com.caju.transaction.domain.transaction.TransactionToAuthorization

/**
 * Representa uma conta do cartão.
 */
data class Account(
    val id: AccountId, val categories: Set<Category>
) {

    /**
     * Processa uma transação na conta.
     *
     * @param transaction Transação a ser processada.
     * @return Categoria atualizada após a transação.
     */
    fun processTransaction(transaction: TransactionToAuthorization): Result<Category> {
        // Busca a categoria autorizada para a transação
        return findAuthorizedCategory(transaction)
            .map { category -> category.debit(transaction.amount) }
            .mapCatching { updatedCategory -> updatedCategory }
    }

    /**
     * Verifica se uma transação pode ser autorizada na conta.
     *
     * @param transaction Transação a ser verificada.
     * @return Categoria autorizada para a transação, ou lança uma exceção se nenhuma categoria for autorizada.
     */
    private fun findAuthorizedCategory(transaction: TransactionToAuthorization): Result<Category> {
        return categories
            .firstOrNull { it.canAuthorize(transaction) }
            ?.let { Result.success(it) }
            ?: Result.failure(InsufficientFounds())
    }
}