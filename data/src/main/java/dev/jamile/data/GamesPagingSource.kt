package dev.jamile.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result

class GamesPagingSource(
    private val loadGames: suspend (page: Int) -> Result<List<Game>>
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val pageNumber = params.key ?: 1
            when (val result = loadGames(pageNumber)) {
                is Result.Success -> {
                    val games = result.data
                    LoadResult.Page(
                        data = games,
                        prevKey = if (pageNumber == 1) null else pageNumber - 1,
                        nextKey = if (games.isEmpty()) null else pageNumber + 1
                    )
                }

                is Result.Error -> {
                    LoadResult.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}