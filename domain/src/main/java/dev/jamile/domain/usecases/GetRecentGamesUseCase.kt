package dev.jamile.domain.usecases

import dev.jamile.domain.models.Game
import dev.jamile.domain.models.Result
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class GetRecentGamesUseCase
    @Inject
    constructor(
        private val repository: GameRepository,
    ) {
        suspend operator fun invoke(page: Int): Result<List<Game>> = repository.getRecentGames(page)
    }
