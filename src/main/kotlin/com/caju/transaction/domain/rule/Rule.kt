package com.caju.transaction.domain.rule

import com.caju.transaction.domain.transaction.TransactionToAuthorization

/**
 * Representa uma regra de autorização.
 */
data class Rule(
    val id: RuleId,
    val conditionals: List<Conditional>,
    val priority: Int
) {

    /**
     * Verifica se uma transação é autorizada pela regra.
     *
     * @param transaction Transação a ser verificada.
     * @return Verdadeiro se a transação é autorizada, falso caso contrário.
     */
    fun isAuthorized(transaction: TransactionToAuthorization): Boolean {
        return conditionals.any { it.evaluate(transaction) }
    }
}