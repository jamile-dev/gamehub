package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class GetMostPopularGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(page: Int): List<Game> {
        return repository.getPopularGames(page)
    }
}