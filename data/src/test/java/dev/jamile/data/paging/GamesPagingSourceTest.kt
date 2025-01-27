package dev.jamile.data.paging

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import dev.jamile.data.GamesPagingSource
import dev.jamile.data.generateFakeGame
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result.Error
import dev.jamile.domain.models.Result.Success
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertIs

class GamesPagingSourceTest {
    @Test
    fun `load returns LoadResult_Page with data when successful`() =
        runTest {
            // Arrange
            val mockGames = listOf(generateFakeGame(), generateFakeGame())
            val pagingSource = GamesPagingSource { Success(mockGames) }

            // Act
            val result =
                pagingSource.load(
                    LoadParams.Refresh(key = 1, loadSize = 2, placeholdersEnabled = false),
                )

            // Assert
            assertIs<LoadResult.Page<Int, Game>>(result)
            assertEquals(mockGames, result.data)
            assertEquals(null, result.prevKey)
            assertEquals(2, result.nextKey)
        }

    @Test
    fun `load returns LoadResult_Page with empty data when no more data`() =
        runTest {
            // Arrange
            val emptyList = emptyList<Game>()
            val pagingSource = GamesPagingSource { Success(emptyList) }

            // Act
            val result =
                pagingSource.load(
                    LoadParams.Refresh(key = 1, loadSize = 2, placeholdersEnabled = false),
                )

            // Assert
            assertIs<LoadResult.Page<Int, Game>>(result)
            assertEquals(emptyList, result.data)
            assertEquals(null, result.prevKey)
            assertEquals(null, result.nextKey)
        }

    @Test
    fun `load returns LoadResult_Error when Result is Error`() =
        runTest {
            // Arrange
            val exception = Exception("Network Error")
            val pagingSource = GamesPagingSource { Error(exception) }

            // Act
            val result =
                pagingSource.load(
                    LoadParams.Refresh(key = 1, loadSize = 2, placeholdersEnabled = false),
                )

            // Assert
            assertIs<LoadResult.Error<Int, Game>>(result)
            assertEquals(exception, result.throwable)
        }

    @Test
    fun `load returns LoadResult_Error when exception is thrown`() =
        runTest {
            // Arrange
            val exception = Exception("Generic Error")
            val pagingSource = GamesPagingSource { throw exception }

            // Act
            val result =
                pagingSource.load(
                    LoadParams.Refresh(key = 1, loadSize = 2, placeholdersEnabled = false),
                )

            // Assert
            assertIs<LoadResult.Error<Int, Game>>(result)
            assertEquals(exception, result.throwable)
        }
}
