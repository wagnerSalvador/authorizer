package com.caju.transaction.infrastructure.mocks

import com.caju.transaction.domain.rule.Rule
import com.caju.transaction.domain.rule.RuleId

object RuleMock {
    // Alimentacao (5411 or 5412) - FOOD
    fun alimentacaoMcc5411Or5412(): Rule {
        return Rule(
            RuleId("123"),
            listOf(
                ConditionalMock.mcc5412Alimentacao(),
                ConditionalMock.mcc5411Alimentacao()
            ),
            0
        )
    }

    // Alimentacao MerchantName - FOOD
    fun alimentacaoMerchantName(): Rule {
        return Rule(
            RuleId("1233"),
            listOf(
                ConditionalMock.merchantNameContainsMercadinho()
            ),
            1
        )
    }

    // Refeicao (5811 or 5812) - MEAL
    fun refeicaoMcc5811Or5812(): Rule {
        return Rule(
            RuleId("1234"),
            listOf(
                ConditionalMock.mcc5811Refeicao(),
                ConditionalMock.mcc5812Refeicao()
            ),
            0
        )
    }

    fun refeicaoMerchantName(): Rule {
        return Rule(
            RuleId("1235"),
            listOf(
                ConditionalMock.merchantNameContainsUberEats()
            ),
            1
        )
    }

}