package dev.jamile.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.jamile.domain.models.GameDetails
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class FavoriteGameDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: GameDatabase
    private lateinit var dao: FavoriteGameDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room
                .inMemoryDatabaseBuilder(
                    context,
                    GameDatabase::class.java,
                ).build()
        dao = database.favoriteGameDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveGame() =
        runTest {
            // Arrange
            // Act
            dao.insertFavoriteGame(gameDetail1)

            // Assert
            val retrievedGames = dao.getAllFavoriteGames().first()
            assertEquals(1, retrievedGames.size)
            assertEquals(gameDetail1, retrievedGames.first())
        }

    @Test
    fun deleteGame_removes_game_from_database() =
        runTest {
            // Arrange
            dao.insertFavoriteGame(gameDetail1)

            // Act
            dao.deleteFavoriteGame(gameDetail1.id.toString())

            // Assert
            val retrievedGame = dao.getAllFavoriteGames().first().firstOrNull()
            assertNull(retrievedGame)
        }

    @Test
    fun getAllFavoriteGames_returns_all_favorite_games() =
        runTest {
            // Arrange
            dao.insertFavoriteGame(gameDetail1)
            dao.insertFavoriteGame(gameDetail2)

            // Act
            val games = dao.getAllFavoriteGames().first()

            // Assert
            assertEquals(listOf(gameDetail1, gameDetail2), games)
        }

    @Test
    fun isGameFavorite_returns_true_when_game_is_favorite() =
        runTest {
            // Arrange
            dao.insertFavoriteGame(gameDetail1)

            // Act
            val isFavorite = dao.isGameFavorite(gameDetail1.id.toString()).first()

            // Assert
            assertTrue(isFavorite)
        }

    @Test
    fun isGameFavorite_returns_false_when_game_is_no_favorite() =
        runTest {
            // Arrange
            val gameId = "non_existent_game_id"

            // Act
            val isFavoriteFlow = dao.isGameFavorite(gameId)
            val isFavorite = isFavoriteFlow.first()

            // Assert
            assertFalse(isFavorite)
        }
}

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
