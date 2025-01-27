package dev.jamile.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jamile.data.local.FavoriteGameDao
import dev.jamile.data.local.GameDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideGameDatabase(
        @ApplicationContext appContext: Context,
    ): GameDatabase =
        Room
            .databaseBuilder(
                appContext,
                GameDatabase::class.java,
                "game_database",
            ).build()

    @Provides
    fun provideFavoriteGameDao(database: GameDatabase): FavoriteGameDao = database.favoriteGameDao()
}
