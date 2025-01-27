package dev.jamile.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jamile.data.GamesPagingSource
import dev.jamile.domain.models.Game
import dev.jamile.domain.usecases.GetMostPopularGamesUseCase
import dev.jamile.domain.usecases.GetRecentGamesUseCase
import dev.jamile.presentation.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularGamesUseCase: GetMostPopularGamesUseCase,
    private val getRecentGamesUseCase: GetRecentGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<HomeGameData>>(UIState.Loading)
    val uiState: StateFlow<UIState<HomeGameData>> = _uiState

    private var popularGamesFlow: Flow<PagingData<Game>>? = null
    private var recentGamesFlow: Flow<PagingData<Game>>? = null

    fun loadGames() = viewModelScope.launch {
        _uiState.value = UIState.Loading

        try {
            popularGamesFlow = popularGamesFlow ?: Pager(PagingConfig(pageSize = 10)) {
                GamesPagingSource { page -> getPopularGamesUseCase(page) }
            }.flow.cachedIn(viewModelScope)

            recentGamesFlow = recentGamesFlow ?: Pager(PagingConfig(pageSize = 10)) {
                GamesPagingSource { page -> getRecentGamesUseCase(page) }
            }.flow.cachedIn(viewModelScope)

            _uiState.value = UIState.Success(HomeGameData(popularGamesFlow!!, recentGamesFlow!!))
        } catch (e: Exception) {
            _uiState.value = UIState.Error(e)
        }
    }
}