package dev.jamile.presentation.ui.home

import androidx.paging.PagingData
import dev.jamile.domain.models.Game
import kotlinx.coroutines.flow.Flow

data class HomeGameData(
    val popularGames: Flow<PagingData<Game>>,
    val recentGames: Flow<PagingData<Game>>
)