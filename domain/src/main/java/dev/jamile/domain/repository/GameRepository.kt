package dev.jamile.domain.repository

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result

interface GameRepository {
    suspend fun getPopularGames(page: Int): List<Game>
    suspend fun getRecentGames(page: Int): List<Game>
    suspend fun searchGamesPaged(query: String, page: Int): List<Game>
    suspend fun getGameDetails(gameId: String): Result<GameDetails>
}