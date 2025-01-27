package dev.jamile.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jamile.data.repository.FavoriteGamesRepositoryImpl
import dev.jamile.domain.repository.FavoriteGamesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFavoriteGamesRepository(favoriteGamesRepositoryImpl: FavoriteGamesRepositoryImpl): FavoriteGamesRepository
}
