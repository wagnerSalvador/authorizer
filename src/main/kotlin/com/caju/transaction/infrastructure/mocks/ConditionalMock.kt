package com.caju.transaction.infrastructure.mocks

import com.caju.transaction.domain.rule.Conditional
import com.caju.transaction.domain.rule.Operator
import com.caju.transaction.domain.rule.Property


object ConditionalMock {

    // Alimentação - FOOD
    fun mcc5411Alimentacao() = Conditional(Property.MCC, Operator.EQUALS, "5411")
    fun mcc5412Alimentacao() = Conditional(Property.MCC, Operator.EQUALS, "5412")
    fun merchantNameContainsMercadinho() = Conditional(Property.MERCHANT_NAME, Operator.CONTAINS, "MERCADINHO")

    // Refeição - MEAL
    fun mcc5811Refeicao() = Conditional(Property.MCC, Operator.EQUALS, "5811")
    fun mcc5812Refeicao() = Conditional(Property.MCC, Operator.EQUALS, "5812")
    fun merchantNameContainsUberEats() = Conditional(Property.MERCHANT_NAME, Operator.CONTAINS, "UBER EATS")
}
