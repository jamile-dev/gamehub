package dev.jamile.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jamile.data.GamesPagingSource
import dev.jamile.domain.usecases.SearchGamesUseCase
import dev.jamile.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchGameViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<SearchGameData>>(UIState.Loading)
    val uiState: StateFlow<UIState<SearchGameData>> = _uiState

    fun searchGames(query: String) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                val searchResultsFlow = Pager(PagingConfig(pageSize = 10)) {
                    GamesPagingSource { page -> searchGamesUseCase.execute(query, page) }
                }.flow.cachedIn(viewModelScope)

                _uiState.value = UIState.Success(SearchGameData(searchResultsFlow))
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}