package com.caju.transaction.domain.rule

import com.caju.transaction.infrastructure.mocks.RuleMock
import com.caju.transaction.infrastructure.mocks.TransactionMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RuleJpaEntityTest {

    @Test
    fun `Rules authorize food mcc 5411`() {
        val rule = RuleMock.alimentacaoMcc5411Or5412()
        assertEquals(true, rule.isAuthorized(TransactionMock.mercadinhoMenorPrecoMcc5411()))
    }

    @Test
    fun `Rules authorize food mcc 5412`() {
        val rule = RuleMock.alimentacaoMcc5411Or5412()
        assertEquals(true, rule.isAuthorized(TransactionMock.carrefurMcc5412()))
    }

    @Test
    fun `Rules not authorize food mcc 5412`() {
        val rule = RuleMock.alimentacaoMcc5411Or5412()
        assertEquals(false, rule.isAuthorized(TransactionMock.uberTripMcc4121()))
    }

    @Test
    fun `Rules authorize transaction food with merchant name`() {
        val rule = RuleMock.alimentacaoMerchantName()
        assertEquals(true, rule.isAuthorized(TransactionMock.mercadinhoMenorPrecoMcc5411()))
    }

    @Test
    fun `Rules not authorize transaction food with merchant name`() {
        val rule = RuleMock.alimentacaoMerchantName()
        assertEquals(false, rule.isAuthorized(TransactionMock.pagJoseDaSilvaMcc5412HighValue()))
    }

    @Test
    fun `Rules authorize meal mcc 5811`() {
        val rule = RuleMock.refeicaoMcc5811Or5812()
        assertEquals(true, rule.isAuthorized(TransactionMock.uberEatsMcc5811()))
    }

    @Test
    fun `Rules authorize meal mcc 5812`() {
        val rule = RuleMock.refeicaoMcc5811Or5812()
        assertEquals(true, rule.isAuthorized(TransactionMock.churrascariaBoiMcc5812()))
    }

    @Test
    fun `Rules not authorize meal mcc 5812`() {
        val rule = RuleMock.refeicaoMcc5811Or5812()
        assertEquals(false, rule.isAuthorized(TransactionMock.picPayBilheteUnicoMcc7523()))
    }

}