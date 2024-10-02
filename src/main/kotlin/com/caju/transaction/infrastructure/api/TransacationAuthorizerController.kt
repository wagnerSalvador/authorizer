package com.caju.transaction.infrastructure.api

import com.caju.transaction.application.account.TransactionAuthorizerOutput
import com.caju.transaction.application.account.TransactionAuthorizerUseCase
import com.caju.transaction.domain.account.AccountId
import com.caju.transaction.domain.transaction.TransactionToAuthorization
import com.caju.transaction.infrastructure.account.model.TransactionToAuthorizationInput
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/accounts/transactions")
class TransacationAuthorizerController(private val useCase: TransactionAuthorizerUseCase) {

    @Post("/authorize")
    fun authorize(@Body body: TransactionToAuthorizationInput): TransactionAuthorizerOutput {
        return useCase.execute(
            TransactionToAuthorization(
                body.merchant,
                body.mcc,
                body.totalAmmout,
                AccountId(body.accountId)
            )
        )
    }
}