package dev.jamile.domain.usecases

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMostPopularGamesUseCaseTest {

    private val getMostPopularGamesUseCase: GetMostPopularGamesUseCase = mockk()

    @Test
    fun `use case throws exception`() = runTest {
        val errorMessage = "Network error"
        coEvery { getMostPopularGamesUseCase.execute(any()) } throws RuntimeException(errorMessage)

        try {
            getMostPopularGamesUseCase.execute(1)
            assertTrue("false", false)
        } catch (e: RuntimeException) {
            assertTrue(e.message == errorMessage)
        }
    }
}