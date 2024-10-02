package com.caju.transaction.domain.category

import com.caju.transaction.infrastructure.mocks.TransactionMock
import com.caju.transaction.infrastructure.mocks.CategoryMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CategoryJpaEntityTest {

    @Test
    fun `Deve autorizar transacao com mcc 5411 categoria FOOD`() {
        val category = CategoryMock.food()

        assertEquals(true, category.canAuthorize(TransactionMock.mercadinhoMenorPrecoMcc5411()))
    }

    @Test
    fun `Deve autorizar transacao com mcc 5412 categoria FOOD`() {
        val category = CategoryMock.food()

        assertEquals(true, category.canAuthorize(TransactionMock.carrefurMcc5412()))
    }


    @Test
    fun `Deve autorizar transacao com merchantName categoria FOOD`() {
        val category = CategoryMock.food()

        assertEquals(true, category.canAuthorize(TransactionMock.mercadinhoMenorPrecoMcc5417()))
    }

    @Test
    fun `Não deve autorizar transacao com mcc 5412 categoria FOOD`() {
        val category = CategoryMock.food()

        assertEquals(false, category.canAuthorize(TransactionMock.uberTripMcc4121()))
    }

    @Test
    fun `Deve autorizar transacao com mcc 5811 categoria MEAL`() {
        val category = CategoryMock.meal()

        assertEquals(true, category.canAuthorize(TransactionMock.uberEatsMcc5811()))
    }

    @Test
    fun `Deve autorizar transacao com mcc 5812 categoria MEAL`() {
        val category = CategoryMock.meal()

        assertEquals(true, category.canAuthorize(TransactionMock.churrascariaBoiMcc5812()))
    }

    @Test
    fun `Deve autorizar transacao com mcc 4121 categoria CASH`() {
        val category = CategoryMock.cash()

        assertEquals(true, category.canAuthorize(TransactionMock.uberTripMcc4121()))
    }

    @Test
    fun `Não deve autorizar transacao  falta de saldo`() {
        val category = CategoryMock.cash()

        assertEquals(false, category.canAuthorize(TransactionMock.pagJoseDaSilvaMcc5412HighValue()))

    }

}