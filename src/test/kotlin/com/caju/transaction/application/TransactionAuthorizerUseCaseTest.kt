package com.caju.transaction.application

import com.caju.transaction.application.account.ResponseCode
import com.caju.transaction.application.account.TransactionAuthorizerUseCase
import com.caju.transaction.domain.account.Account
import com.caju.transaction.domain.account.AccountId
import com.caju.transaction.domain.account.AccountRepository
import com.caju.transaction.domain.category.Category
import com.caju.transaction.infrastructure.mocks.CategoryMock
import com.caju.transaction.domain.category.CategoryRepository
import com.caju.transaction.infrastructure.mocks.TransactionMock
import com.caju.transaction.domain.transaction.TransactionToAuthorization
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TransactionAuthorizerUseCaseTest {

    private val accountRepository = mockk<AccountRepository>()
    private val categoryRepository = mockk<CategoryRepository>()

    private lateinit var useCase: TransactionAuthorizerUseCase

    @BeforeEach
    fun setup() {
        useCase = TransactionAuthorizerUseCase(accountRepository, categoryRepository)
    }

    @Test
    fun `deve aprovar a transacao quando houver saldo suficiente categoria FOOD - Autorizador simples`() {
        // Arrange: Configurar o cenário de teste
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        val account = Account(accountId, setOf(categoryFood, categoryMeal, categoryCash))
        val transaction = TransactionMock.mercadinhoMenorPrecoMcc5411()

        val input = TransactionToAuthorization(
            accountId = accountId, merchant = transaction.merchant, mcc = transaction.mcc, amount = BigDecimal(50.0)
        )

        // Configura o comportamento dos mocks
        every { accountRepository.findById(accountId) } returns Result.success(account)
        every { categoryRepository.save(any()) } just Runs

        // Act: Executa o use case
        val result = useCase.execute(input)

        // Assert: Verifica se o resultado foi o esperado
        assertEquals(ResponseCode.APPROVED, result.code)

        val slot = slot<Category>()
        verify { categoryRepository.save(capture(slot)) }

        val updatedCategory = slot.captured
        assertEquals(categoryFood.id, updatedCategory.id)
        assertEquals(BigDecimal(50.0), updatedCategory.balance)
    }

    @Test
    fun `deve aprovar a transacao quando houver saldo suficiente categoria MEAL - L1 - Autorizador Simples`() {
        // Arrange: Configurar o cenário de teste
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        val account = Account(accountId, setOf(categoryFood, categoryMeal, categoryCash))
        val transaction = TransactionMock.churrascariaBoiMcc5812()

        val input = TransactionToAuthorization(
            accountId = accountId, merchant = transaction.merchant, mcc = transaction.mcc, amount = BigDecimal(50.0)
        )

        // Configura o comportamento dos mocks
        every { accountRepository.findById(accountId) } returns Result.success(account)
        every { categoryRepository.save(any()) } just Runs

        // Act: Executa o use case
        val result = useCase.execute(input)

        // Assert: Verifica se o resultado foi o esperado
        assertEquals(ResponseCode.APPROVED, result.code)

        val slot = slot<Category>()
        verify { categoryRepository.save(capture(slot)) }

        val updatedCategory = slot.captured
        assertEquals(categoryMeal.id, updatedCategory.id)
        assertEquals(BigDecimal(50.0), updatedCategory.balance)
    }

    @Test
    fun `deve aprovar a transacao quando houver saldo suficiente categoria CASH, iria para categoria MEAL porem sem saldo - L2 - FALLBACK`() {
        // Arrange: Configurar o cenário de teste
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        val account = Account(accountId, setOf(categoryFood, categoryMeal, categoryCash))
        val transaction = TransactionMock.churrascariaBoiMcc5812()

        val input = TransactionToAuthorization(
            accountId = accountId, merchant = transaction.merchant, mcc = transaction.mcc, amount = BigDecimal(150.0)
        )

        // Configura o comportamento dos mocks
        every { accountRepository.findById(accountId) } returns Result.success(account)
        every { categoryRepository.save(any()) } just Runs

        // Act: Executa o use case
        val result = useCase.execute(input)

        // Assert: Verifica se o resultado foi o esperado
        assertEquals(ResponseCode.APPROVED, result.code)

        val slot = slot<Category>()
        verify { categoryRepository.save(capture(slot)) }

        val updatedCategory = slot.captured
        assertEquals(categoryCash.id, updatedCategory.id)
        assertEquals(BigDecimal(50.0), updatedCategory.balance)
    }

    @Test
    fun `deve aprovar a transacao quando houver saldo suficiente categoria MEAL, L3 - Depende do comerciante, prioridade sobre MCC`() {
        // Arrange: Configurar o cenário de teste
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        val account = Account(accountId, setOf(categoryFood, categoryMeal, categoryCash))
        val transaction = TransactionMock.uberEatsMcc5811()

        val input = TransactionToAuthorization(
            accountId = accountId, merchant = transaction.merchant, mcc = "5858", amount = BigDecimal(50.0)
        )

        // Configura o comportamento dos mocks
        every { accountRepository.findById(accountId) } returns Result.success(account)
        every { categoryRepository.save(any()) } just Runs

        // Act: Executa o use case
        val result = useCase.execute(input)

        // Assert: Verifica se o resultado foi o esperado
        assertEquals(ResponseCode.APPROVED, result.code)

        val slot = slot<Category>()
        verify { categoryRepository.save(capture(slot)) }

        val updatedCategory = slot.captured
        assertEquals(categoryMeal.id, updatedCategory.id)
        assertEquals(BigDecimal(50.0), updatedCategory.balance)
    }

    @Test
    fun `deve rejeitar a transacao quando nao houver saldo suficiente`() {
        // Arrange
        // Arrange: Configurar o cenário de teste
        val accountId = AccountId("123")
        val categoryFood = CategoryMock.food()
        val categoryMeal = CategoryMock.meal()
        val categoryCash = CategoryMock.cash()
        val account = Account(accountId, setOf(categoryFood, categoryMeal, categoryCash))
        val transaction = TransactionMock.churrascariaBoiMcc5812()

        val input = TransactionToAuthorization(
            accountId = accountId, merchant = transaction.merchant, mcc = transaction.mcc, amount = BigDecimal(250.0)
        )

        every { accountRepository.findById(accountId) } returns Result.success(account)

        // Act
        val result = useCase.execute(input)

        // Assert
        assertEquals(ResponseCode.INSUFFICIENT_FUNDS, result.code)

        // Verifica que a categoria não foi salva já que o saldo é insuficiente
        verify(exactly = 0) { categoryRepository.save(any()) }
    }

    @Test
    fun `deve retornar erro interno quando a conta nao for encontrada`() {
        // Arrange
        val accountId = AccountId("123")
        val input = TransactionToAuthorization(
            accountId = accountId, merchant = "Some Merchant", mcc = "5411", amount = BigDecimal.valueOf(50.0)
        )

        // Configura o mock para retornar uma falha
        every { accountRepository.findById(accountId) } returns Result.failure(Exception("Conta não encontrada"))

        // Act
        val result = useCase.execute(input)

        // Assert
        assertEquals(ResponseCode.INTERNAL_ERROR, result.code)

        // Verifica que a categoria não foi salva, pois a conta não foi encontrada
        verify(exactly = 0) { categoryRepository.save(any()) }
    }
}