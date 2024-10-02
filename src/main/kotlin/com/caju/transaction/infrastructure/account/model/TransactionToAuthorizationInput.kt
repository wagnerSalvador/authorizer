package com.caju.transaction.infrastructure.account.model

import java.math.BigDecimal

data class TransactionToAuthorizationInput(
    val accountId: String,
    val totalAmmout: BigDecimal,
    val mcc: String,
    val merchant: String
)


