package dev.jamile.data.repository

import dev.jamile.data.local.FavoriteGameDao
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.repository.FavoriteGamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteGamesRepositoryImpl
    @Inject
    constructor(
        private val favoriteGameDao: FavoriteGameDao,
    ) : FavoriteGamesRepository {
        override suspend fun insertFavoriteGame(game: GameDetails) {
            favoriteGameDao.insertFavoriteGame(game)
        }

        override suspend fun deleteFavoriteGame(gameId: String) {
            favoriteGameDao.deleteFavoriteGame(gameId)
        }

        override fun getAllFavoriteGames(): Flow<List<GameDetails>> = favoriteGameDao.getAllFavoriteGames()

        override fun isGameFavorite(gameId: String): Flow<Boolean> = favoriteGameDao.isGameFavorite(gameId)
    }
