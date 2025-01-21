package dev.jamile.data.repository

import dev.jamile.data.remote.GameApi
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApiService: GameApi
) : GameRepository {

    override suspend fun getPopularGames(): List<Game> {
        val response = gameApiService.getPopularGames()
        return response.results.map { it.toDomainModel() }
    }

    override suspend fun getRecentGames(): List<Game> {
        val response = gameApiService.getMostRecentGames()
        return response.results.map { it.toDomainModel() }
    }

    override suspend fun searchGames(query: String): List<Game> {
        val response = gameApiService.searchGames(query)
        return response.results.map { it.toDomainModel() }
    }

    override suspend fun getGameDetails(gameId: String): GameDetails {
        val response = gameApiService.getGameDetails(gameId)
        return response.toDomainModel()
    }
}