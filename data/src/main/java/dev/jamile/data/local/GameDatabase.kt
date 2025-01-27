package dev.jamile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.jamile.data.converters.Converters
import dev.jamile.domain.models.GameDetails

@Database(entities = [GameDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun favoriteGameDao(): FavoriteGameDao
}