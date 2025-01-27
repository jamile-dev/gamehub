package dev.jamile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jamile.domain.models.GameDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(game: GameDetails)

    @Query("DELETE FROM favorite_games WHERE id = :gameId")
    suspend fun deleteFavoriteGame(gameId: String)

    @Query("SELECT * FROM favorite_games")
    fun getAllFavoriteGames(): Flow<List<GameDetails>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_games WHERE id = :gameId)")
    fun isGameFavorite(gameId: String): Flow<Boolean>
}