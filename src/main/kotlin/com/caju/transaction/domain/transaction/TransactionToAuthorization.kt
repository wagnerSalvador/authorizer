package com.caju.transaction.domain.transaction

import com.caju.transaction.domain.account.AccountId
import com.caju.transaction.domain.rule.Property
import java.math.BigDecimal

/**
 * Representa uma transação.
 */
data class TransactionToAuthorization(
    val merchant: String,
    val mcc: String,
    val amount: BigDecimal,
    val accountId: AccountId
) {

    /**
     * Obtém o valor de uma propriedade da transação.
     *
     * @param property Propriedade a ser obtida.
     * @return Valor da propriedade.
     */
    fun getValue(property: Property): String {
        return when (property) {
            Property.MCC -> mcc
            Property.MERCHANT_NAME -> merchant
        }
    }
}