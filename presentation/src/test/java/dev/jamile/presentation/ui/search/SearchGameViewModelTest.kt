package dev.jamile.presentation.ui.search

import dev.jamile.data.generateFakeGame
import dev.jamile.domain.models.Result
import dev.jamile.domain.usecases.SearchGamesUseCase
import dev.jamile.presentation.state.UIState
import dev.jamile.testsupport.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGameViewModelTest : BaseViewModelTest() {
    private val searchGamesUseCase: SearchGamesUseCase = mockk()

    private lateinit var viewModel: SearchGameViewModel

    @Before
    fun setup() {
        viewModel = SearchGameViewModel(searchGamesUseCase)
    }

    @Test
    fun `searchGames updates uiState to Success when UseCase returns Success`() =
        runTest {
            // Arrange
            val query = "test game"
            val result = listOf(generateFakeGame("2"))
            coEvery { searchGamesUseCase(query, any()) } returns Result.Success(result)

            // Act
            viewModel.onSearchQueryChange(query)

            // Assert
            val uiStates = mutableListOf<UIState<SearchGameData>>()
            val job =
                launch {
                    viewModel.uiState.collect { uiStates.add(it) }
                }

            advanceUntilIdle()

            assertTrue(uiStates.any { it is UIState.Success })
            job.cancel()
        }

    @Test
    fun `searchGames does not call UseCase with empty query`() =
        runTest {
            // Arrange
            val query = ""
            val uiStates = mutableListOf<UIState<SearchGameData?>>()
            val job =
                launch {
                    viewModel.uiState.collect { uiStates.add(it) }
                }

            // Act
            viewModel.onSearchQueryChange(query)
            advanceUntilIdle()

            // Assert
            coVerify(exactly = 0) {
                searchGamesUseCase(any(), any())
            }
            assertEquals(UIState.Loading, viewModel.uiState.value)
            job.cancel()
        }
}
