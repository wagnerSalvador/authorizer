package com.caju.transaction.application.account

import com.caju.transaction.domain.account.AccountRepository
import com.caju.transaction.domain.category.CategoryRepository
import com.caju.transaction.domain.exceptions.InsufficientFounds
import com.caju.transaction.domain.transaction.TransactionToAuthorization
import jakarta.inject.Singleton

/**
 * Use case para autorizar transações.
 */
@Singleton
class TransactionAuthorizerUseCase(
    private val accountRepository: AccountRepository,
    private val categoryRepository: CategoryRepository
) {

    /**
     * Executa o use case para autorizar uma transação.
     *
     * @param input Comando para autorizar a transação.
     */
    fun execute(input: TransactionToAuthorization): TransactionAuthorizerOutput {
        return accountRepository.findById(input.accountId)
            .map { account ->
                account.processTransaction(input)
                    .map { category ->
                        categoryRepository.save(category)
                        TransactionAuthorizerOutput(ResponseCode.APPROVED)
                    }
                    .getOrElse { exception ->
                        when (exception) {
                            is InsufficientFounds -> TransactionAuthorizerOutput(ResponseCode.INSUFFICIENT_FUNDS)
                            else -> TransactionAuthorizerOutput(ResponseCode.INTERNAL_ERROR)
                        }
                    }
            }
            .getOrElse { TransactionAuthorizerOutput(ResponseCode.INTERNAL_ERROR) }
    }
}