package dev.jamile.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.jamile.domain.models.Game

class GamesPagingSource(
    private val getGamesUseCase: suspend (Int) -> List<Game>
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 1
            val games = getGamesUseCase(page)
            LoadResult.Page(
                data = games,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (games.isEmpty()) null else page + 1
            )
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