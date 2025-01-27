package dev.jamile.presentation.ui.favorites

import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.repository.FavoriteGamesRepository
import dev.jamile.presentation.state.UIState
import dev.jamile.testsupport.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest : BaseViewModelTest() {
    private val favoriteGamesRepository: FavoriteGamesRepository = mockk()

    private lateinit var viewModel: FavoritesViewModel

    @Before
    fun setup() {
        viewModel = FavoritesViewModel(favoriteGamesRepository)
    }

    @Test
    fun `loadFavoriteGames updates uiState to Success with games`() =
        runTest {
            // Arrange
            val mockGames = listOf(gameDetail1, gameDetail2)
            coEvery { favoriteGamesRepository.getAllFavoriteGames() } returns flowOf(mockGames)

            // Act
            viewModel.loadFavoriteGames()

            // Assert
            val uiStates = mutableListOf<UIState<List<GameDetails>>>()
            val job =
                launch {
                    viewModel.uiState.collect { uiStates.add(it) }
                }
            advanceUntilIdle()
            assertEquals(UIState.Success(mockGames), uiStates.last())
            job.cancel()
        }

    @Test
    fun `loadFavoriteGames updates uiState to Error when repository throws exception`() =
        runTest {
            // Arrange
            val exception = Exception("Database error")
            coEvery { favoriteGamesRepository.getAllFavoriteGames() } throws exception

            // Act
            viewModel.loadFavoriteGames()

            // Assert
            val uiStates = mutableListOf<UIState<List<GameDetails>>>()
            val job =
                launch {
                    viewModel.uiState.collect { uiStates.add(it) }
                }
            advanceUntilIdle()
            assertTrue(uiStates.any { it is UIState.Error && it.exception == exception })
            job.cancel()
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
