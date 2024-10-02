package com.caju.transaction.domain.rule

import com.caju.transaction.infrastructure.mocks.ConditionalMock
import com.caju.transaction.infrastructure.mocks.TransactionMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConditionalTest {

    @Test
    fun `deve avaliar condicional de igualdade mcc 5412 alimentacao true`() {
        val conditional = ConditionalMock.mcc5412Alimentacao()
        val transaction = TransactionMock.carrefurMcc5412()
        assertEquals(true, conditional.evaluate(transaction))
    }

    @Test
    fun `deve avaliar condicional de igualdade mcc 5412 alimentacao false`() {
        val conditional = ConditionalMock.mcc5412Alimentacao()
        val transaction = TransactionMock.uberEatsMcc5811()
        assertEquals(false, conditional.evaluate(transaction))
    }

    @Test
    fun `deve avaliar condicional de contem mechantName 5412 alimentacao true`() {
        val conditional = ConditionalMock.merchantNameContainsMercadinho()
        val transaction = TransactionMock.mercadinhoMenorPrecoMcc5411()
        assertEquals(true, conditional.evaluate(transaction))
    }

    @Test
    fun `deve avaliar condicional de igualdade mcc 5812 refeicao true`() {
        val conditional = ConditionalMock.mcc5812Refeicao()
        val transaction = TransactionMock.churrascariaBoiMcc5812()
        assertEquals(true, conditional.evaluate(transaction))
    }

    @Test
    fun `deve avaliar condicional de igualdade mcc 5811 refeicao false`() {
        val conditional = ConditionalMock.mcc5812Refeicao()
        val transaction = TransactionMock.carrefurMcc5412()
        assertEquals(false, conditional.evaluate(transaction))
    }

    @Test
    fun `deve avaliar condicional de contem mechantName 5811 refeicao true`() {
        val conditional = ConditionalMock.merchantNameContainsUberEats()
        val transaction = TransactionMock.uberEatsMcc5811()
        assertEquals(true, conditional.evaluate(transaction))
    }

}