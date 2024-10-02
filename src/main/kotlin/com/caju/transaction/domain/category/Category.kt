package com.caju.transaction.domain.category

import com.caju.transaction.domain.rule.Rule
import com.caju.transaction.domain.transaction.TransactionToAuthorization
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Representa uma categoria de transação.
 */
data class Category(
    val id: CategoryId,
    val name: String,
    val balance: BigDecimal,
    val rules: List<Rule>,
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

    /**
     * Verifica se uma transação pode ser autorizada pela categoria.
     *
     * @param transaction Transação a ser verificada.
     * @return Verdadeiro se a transação pode ser autorizada, falso caso contrário.
     */
    fun canAuthorize(transaction: TransactionToAuthorization): Boolean {
        return hasBalanceAvaible(transaction) and (
                rules.isEmpty()
                        or rules.sortedByDescending { it.priority }.any { it.isAuthorized(transaction) }
                )
    }


    /**
     * Verifica se uma transação pode ser debitada da categoria.
     *
     * @param transaction Transação a ser verificada.
     * @return Verdadeiro se a transação pode ser debitada, falso caso contrário.
     */
    private fun hasBalanceAvaible(transaction: TransactionToAuthorization): Boolean {
        return balance > transaction.amount
    }

    /**
     * Debita um valor da categoria.
     *
     * @param amount Valor a ser debitado.
     * @return Categoria atualizada após o débito.
     */
    fun debit(amount: BigDecimal): Category {
        return this.copy(balance = balance - amount, updatedAt = LocalDateTime.now())
    }
}