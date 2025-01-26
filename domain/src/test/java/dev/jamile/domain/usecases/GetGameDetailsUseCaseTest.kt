package dev.jamile.domain.usecases

import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result.Error
import dev.jamile.domain.models.Result.Success
import dev.jamile.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetGameDetailsUseCaseTest {

    private val gameRepository: GameRepository = mockk()
    private val useCase = GetGameDetailsUseCase(gameRepository)

    @Test
    fun `execute should return Success with game details from repository`() = runTest {
        // Arrange
        val mockGameDetails = GameDetails(
            id = 1,
            name = "Fake Game",
            description = "A fake game description.",
            metacritic = 85,
            released = "2024-07-20",
            backgroundImage = "https://example.com/image.jpg",
            rating = 4.5,
            platforms = listOf("PC", "PS5"),
            genres = listOf("Action", "Adventure")
        )
        coEvery { gameRepository.getGameDetails(any()) } returns Success(
            mockGameDetails
        )

        // Act
        val result = useCase.execute("1")

        // Assert
        assertEquals(Success(mockGameDetails), result)
    }

    @Test
    fun `execute should return Error when repository throws exception`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { gameRepository.getGameDetails(any()) } returns Error(exception)

        // Act
        val result = useCase.execute("1")

        // Assert
        assertEquals(Error(exception), result)
    }
}