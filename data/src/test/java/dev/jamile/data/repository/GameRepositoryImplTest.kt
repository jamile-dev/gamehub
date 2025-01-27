package dev.jamile.data.repository

import dev.jamile.data.local.FavoriteGameDao
import dev.jamile.domain.models.GameDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class FavoriteGamesRepositoryImplTest {
    private val favoriteGameDao: FavoriteGameDao = mockk()
    private val repository = FavoriteGamesRepositoryImpl(favoriteGameDao)

    @Test
    fun `insertFavoriteGame inserts game into dao`() =
        runTest {
            // Arrange
            coEvery { favoriteGameDao.insertFavoriteGame(gameDetail1) } returns Unit

            // Act
            repository.insertFavoriteGame(gameDetail1)

            // Assert
            coVerify(exactly = 1) { favoriteGameDao.insertFavoriteGame(gameDetail1) }
        }

    @Test
    fun `deleteFavoriteGame deletes game from dao`() =
        runTest {
            // Arrange
            val id = gameDetail2.id.toString()
            coEvery { favoriteGameDao.deleteFavoriteGame(id) } returns Unit

            // Act
            repository.deleteFavoriteGame(id)

            // Assert
            coVerify(exactly = 1) { favoriteGameDao.deleteFavoriteGame(id) }
        }

    @Test
    fun `getAllFavoriteGames returns games from dao`() =
        runTest {
            // Arrange
            val games = listOf(gameDetail1, gameDetail2)
            coEvery { favoriteGameDao.getAllFavoriteGames() } returns flowOf(games)

            // Act
            val result = repository.getAllFavoriteGames().first()

            // Assert
            assertEquals(games, result)
        }

    @Test
    fun `isGameFavorite returns true when game is favorite`() =
        runTest {
            // Arrange
            val gameId = "1"
            coEvery { favoriteGameDao.isGameFavorite(gameId) } returns flowOf(true)

            // Act
            val result = repository.isGameFavorite(gameId).first()

            // Assert
            assertTrue(result)
        }

    @Test
    fun `isGameFavorite returns false when game is not favorite`() =
        runTest {
            // Arrange
            val gameId = "1"
            coEvery { favoriteGameDao.isGameFavorite(gameId) } returns flowOf(false)

            // Act
            val result = repository.isGameFavorite(gameId).first()

            // Assert
            assertFalse(result)
        }
}

// TODO: improve or move this fake data declaration
val gameDetail1 =
    GameDetails(
        id = 1,
        name = "Fake Game Details",
        description = "A fake game description.",
        metacritic = Random.nextInt(0, 100),
        released = "2024-07-20",
        backgroundImage = "https://example.com/image.jpg",
        rating = Random.nextDouble(0.0, 5.0),
        platforms = listOf("PC", "PS5"),
        genres = listOf("Action", "Adventure"),
    )

val gameDetail2 =
    GameDetails(
        id = 2,
        name = "Fake Game Details",
        description = "A fake game description.",
        metacritic = Random.nextInt(0, 100),
        released = "2024-07-20",
        backgroundImage = "https://example.com/image.jpg",
        rating = Random.nextDouble(0.0, 5.0),
        platforms = listOf("PC", "PS5"),
        genres = listOf("Action", "Adventure"),
    )
