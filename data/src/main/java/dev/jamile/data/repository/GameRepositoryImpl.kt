package dev.jamile.data.repository

import dev.jamile.data.remote.GameApi
import dev.jamile.data.utils.excludeTags
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.repository.GameRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_DATE
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApiService: GameApi
) : GameRepository {

    override suspend fun getPopularGames(page: Int): List<Game> {
        val response = gameApiService.getPopularGames(page = page)
        return response.results
            .map { it.toDomainModel() }
            .filter { game -> game.tags?.none { it in excludeTags } ?: true }
    }

    override suspend fun getRecentGames(page: Int): List<Game> {
        val currentDate = LocalDate.now()
        val lastYearDate = currentDate.minusYears(1)
        val dateRange = "${lastYearDate.format(ISO_DATE)},${
            currentDate.format(
                ISO_DATE
            )
        }"
        val response = gameApiService.getMostRecentGames(page = page, dates = dateRange)
        return response.results
            .map { it.toDomainModel() }
            .filter { game -> game.tags?.none { it in excludeTags } ?: true }
    }

    override suspend fun searchGamesPaged(query: String, page: Int): List<Game> {
        val response = gameApiService.searchGames(query, page)
        return response.results.map { it.toDomainModel() }
    }

    override suspend fun getGameDetails(gameId: String): GameDetails {
        val response = gameApiService.getGameDetails(gameId)
        return response.toDomainModel()
    }
}
