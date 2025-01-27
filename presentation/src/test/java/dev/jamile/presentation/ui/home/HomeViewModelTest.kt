package dev.jamile.presentation.ui.home

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result.Success
import dev.jamile.domain.usecases.GetMostPopularGamesUseCase
import dev.jamile.domain.usecases.GetRecentGamesUseCase
import dev.jamile.presentation.state.UIState
import dev.jamile.testsupport.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class HomeViewModelTest : BaseViewModelTest() {
    private val getPopularGamesUseCase: GetMostPopularGamesUseCase = mockk()
    private val getRecentGamesUseCase: GetRecentGamesUseCase = mockk()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(getPopularGamesUseCase, getRecentGamesUseCase)
    }

    @Test
    fun `initial uiState is Loading`() =
        runTest {
            assertEquals(UIState.Loading, viewModel.uiState.value)
        }

    @Test
    fun `loadGames success`() =
        runTest {
            val mockPopularGames = Success(listOf(fakeGame1))
            val mockRecentGames = Success(listOf(fakeGame2))

            coEvery { getPopularGamesUseCase(any()) } returns mockPopularGames
            coEvery { getRecentGamesUseCase(any()) } returns mockRecentGames

            viewModel.loadGames()
            advanceUntilIdle()

            assertTrue(viewModel.uiState.value is UIState.Success)
        }
}

val fakeGame1 =
    Game(
        id = "1",
        name = "Fake Game ${Random.nextInt(100)}",
        genres = listOf("Action", "Adventure", "RPG"),
        platforms = listOf("PC", "PlayStation 5", "Xbox Series X"),
        imageUrl = "https://via.placeholder.com/150",
        rating = Random.nextDouble(1.0, 5.0),
        metaScore = Random.nextInt(50, 100),
        releaseDate = "2024-07-20",
        description = "This is a fake game description.",
        tags = listOf("Singleplayer", "Multiplayer", "Open World"),
    )

val fakeGame2 =
    Game(
        id = "2",
        name = "Fake Game ${Random.nextInt(100)}",
        genres = listOf("Action", "Adventure", "RPG"),
        platforms = listOf("PC", "PlayStation 5", "Xbox Series X"),
        imageUrl = "https://via.placeholder.com/150",
        rating = Random.nextDouble(1.0, 5.0),
        metaScore = Random.nextInt(50, 100),
        releaseDate = "2024-07-20",
        description = "This is a fake game description.",
        tags = listOf("Singleplayer", "Multiplayer", "Open World"),
    )
