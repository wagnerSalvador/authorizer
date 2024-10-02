package com.caju.transaction.domain.rule

import com.caju.transaction.domain.transaction.TransactionToAuthorization

/**
 * Representa uma condição para uma regra de autorização.
 */
data class Conditional(
    val property: Property,
    val operator: Operator,
    val comparativeValue: String
) {

    /**
     * Avalia a condição para uma transação.
     *
     * @param transaction Transação a ser avaliada.
     * @return Verdadeiro se a condição é satisfeita, falso caso contrário.
     */
    fun evaluate(transaction: TransactionToAuthorization): Boolean {
        return when (operator) {
            Operator.EQUALS -> transaction.getValue(property) == comparativeValue
            Operator.CONTAINS -> transaction.getValue(property).contains(comparativeValue)
        }
    }
}