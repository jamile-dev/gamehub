package dev.jamile.domain.repository

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails

interface GameRepository {
    suspend fun getPopularGames(page: Int): List<Game>
    suspend fun getRecentGames(page: Int): List<Game>
    suspend fun searchGames(query: String): List<Game>
    suspend fun getGameDetails(gameId: String): GameDetails
}