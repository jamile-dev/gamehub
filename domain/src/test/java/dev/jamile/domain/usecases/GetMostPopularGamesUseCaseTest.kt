package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result.Error
import dev.jamile.domain.models.Result.Success
import dev.jamile.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi

class GetPopularGamesUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val useCase = GetMostPopularGamesUseCase(gameRepository)

    @Test
    fun `invoke should return Success with games from repository`() = runTest {
        // Arrange
        val mockGames = listOf(
            Game(
                id = "1",
                name = "Fake Game",
                description = "A fake game description.",
                rating = 4.5,
                platforms = listOf("PC", "PS5"),
                genres = listOf("Action", "Adventure"),
                tags = emptyList<String>(),
                imageUrl = "",
                metaScore = 0,
                releaseDate = ""
            )
        )
        coEvery { gameRepository.getPopularGames(any()) } returns Success(mockGames)

        // Act
        val result = useCase(1)

        // Assert
        assertEquals(Success(mockGames), result)
    }

    @Test
    fun `invoke should return Error when repository throws exception`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { gameRepository.getPopularGames(any()) } returns Error(exception)

        // Act
        val result = useCase(1)

        // Assert
        assertEquals(Error(exception), result)
    }
}