package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class GetRecentGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(): List<Game> {
        return repository.getRecentGames()
    }
}