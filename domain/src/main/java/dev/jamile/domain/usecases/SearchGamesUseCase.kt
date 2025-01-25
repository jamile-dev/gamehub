package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(query: String, page: Int): List<Game> {
        return repository.searchGamesPaged(query, page)
    }
}