package dev.jamile.domain.repository

import dev.jamile.domain.models.GameDetails
import kotlinx.coroutines.flow.Flow

interface FavoriteGamesRepository {
    suspend fun insertFavoriteGame(game: GameDetails)

    suspend fun deleteFavoriteGame(gameId: String)

    fun getAllFavoriteGames(): Flow<List<GameDetails>>

    fun isGameFavorite(gameId: String): Flow<Boolean>
}
