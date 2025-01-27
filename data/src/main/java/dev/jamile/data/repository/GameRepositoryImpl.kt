package dev.jamile.data.repository

import dev.jamile.data.remote.GameApi
import dev.jamile.data.utils.excludeTags
import dev.jamile.domain.models.Game
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result
import dev.jamile.domain.repository.GameRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_DATE
import javax.inject.Inject

class GameRepositoryImpl
    @Inject
    constructor(
        private val gameApi: GameApi,
    ) : GameRepository {
        override suspend fun getPopularGames(page: Int): Result<List<Game>> =
            try {
                val response = gameApi.getPopularGames(page = page)
                val games =
                    response.results
                        .map { it.toDomainModel() }
                        .filter { game -> game.tags?.none { it in excludeTags } ?: true }
                Result.Success(games)
            } catch (e: Exception) {
                Result.Error(e)
            }

        override suspend fun getRecentGames(page: Int): Result<List<Game>> =
            try {
                val currentDate = LocalDate.now()
                val lastYearDate = currentDate.minusYears(1)
                val dateRange = "${lastYearDate.format(ISO_DATE)},${currentDate.format(ISO_DATE)}"
                val response = gameApi.getMostRecentGames(page = page, dates = dateRange)
                val games =
                    response.results
                        .map { it.toDomainModel() }
                        .filter { game -> game.tags?.none { it in excludeTags } ?: true }
                Result.Success(games)
            } catch (e: Exception) {
                Result.Error(e)
            }

        override suspend fun searchGamesPaged(
            query: String,
            page: Int,
        ): Result<List<Game>> =
            try {
                val response = gameApi.searchGames(query, page)
                val games = response.results.map { it.toDomainModel() }
                Result.Success(games)
            } catch (e: Exception) {
                Result.Error(e)
            }

        override suspend fun getGameDetails(gameId: String): Result<GameDetails> =
            try {
                val response = gameApi.getGameDetails(gameId)
                Result.Success(response.toDomainModel())
            } catch (e: Exception) {
                Result.Error(e)
            }
    }
