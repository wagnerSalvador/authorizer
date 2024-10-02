package com.caju.transaction.infrastructure.mocks

import com.caju.transaction.domain.account.AccountId
import com.caju.transaction.domain.transaction.TransactionToAuthorization
import java.math.BigDecimal

object TransactionMock {

    // Alimentação - FOOD
    fun mercadinhoMenorPrecoMcc5411() =
        TransactionToAuthorization("MERCADINHO MENOR PRECO", "5411", BigDecimal.TEN, AccountId("123"))

    fun mercadinhoMenorPrecoMcc5417() =
        TransactionToAuthorization("MERCADINHO MENOR PRECO", "5417", BigDecimal.TEN, AccountId("123"))

    fun carrefurMcc5412() = TransactionToAuthorization("CARREFOUR BR", "5412", BigDecimal.TEN, AccountId("123"))

    // Refeição - MEAL
    fun uberEatsMcc5811() = TransactionToAuthorization("UBER EATS SAO PAULO BR", "5811", BigDecimal.TEN, AccountId("123"))
    fun churrascariaBoiMcc5812() =
        TransactionToAuthorization("CHURRASCARIA BOI NA BR", "5812", BigDecimal.TEN, AccountId("123"))

    // Livre - CASH
    fun uberTripMcc4121() = TransactionToAuthorization("UBER TRIP SAO PAULO BR", "4121", BigDecimal.TEN, AccountId("123"))
    fun picPayBilheteUnicoMcc7523() =
        TransactionToAuthorization("`PICPAY*BILHETEUNICO GOIANIA BR`", "7523", BigDecimal.TEN, AccountId("123"))

    fun pagJoseDaSilvaMcc5412HighValue() =
        TransactionToAuthorization("PAG.JOSE DA SILVA BR", "5412", BigDecimal.valueOf(10000), AccountId("123"))

}