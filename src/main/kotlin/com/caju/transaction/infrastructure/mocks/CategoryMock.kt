package com.caju.transaction.infrastructure.mocks

import com.caju.transaction.domain.category.Category
import com.caju.transaction.domain.category.CategoryId
import java.math.BigDecimal
import java.time.LocalDateTime

object CategoryMock {

    fun food(): Category {
        return Category(
            CategoryId("124"),
            "Food",
            BigDecimal.valueOf(100),
            listOf(RuleMock.alimentacaoMcc5411Or5412(), RuleMock.alimentacaoMerchantName()),
            LocalDateTime.now()
        )
    }

    fun meal(): Category {
        return Category(
            CategoryId("125"),
            "Meal",
            BigDecimal.valueOf(100),
            listOf(RuleMock.refeicaoMcc5811Or5812(), RuleMock.refeicaoMerchantName()),
            LocalDateTime.now()
        )
    }

    fun cash(): Category {
        return Category(
            CategoryId("126"),
            "Cash",
            BigDecimal.valueOf(200),
            emptyList(),
            LocalDateTime.now()
        )
    }

}
