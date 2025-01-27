package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(query: String, page: Int): Result<List<Game>> =
        repository.searchGamesPaged(query, page)
}
