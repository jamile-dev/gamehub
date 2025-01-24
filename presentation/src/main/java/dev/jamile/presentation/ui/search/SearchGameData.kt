package dev.jamile.presentation.ui.search

import androidx.paging.PagingData
import dev.jamile.domain.models.Game
import kotlinx.coroutines.flow.Flow

data class SearchGameData(
    val games: Flow<PagingData<Game>>
)