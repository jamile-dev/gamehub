package dev.jamile.domain.usecases

import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result
import dev.jamile.domain.repository.GameRepository
import javax.inject.Inject

class GetGameDetailsUseCase
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend fun execute(gameId: String): Result<GameDetails> = gameRepository.getGameDetails(gameId)
    }
