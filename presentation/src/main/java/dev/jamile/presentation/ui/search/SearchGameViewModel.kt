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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchGameViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<SearchGameData>>(UIState.Loading)
    val uiState: StateFlow<UIState<SearchGameData>> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        viewModelScope.launch {
            searchQuery
                .filter { it.isNotBlank() }
                .collectLatest { query ->
                    _uiState.value = UIState.Loading
                    try {
                        val searchResultsFlow = Pager(PagingConfig(pageSize = 10)) {
                            GamesPagingSource { page -> searchGamesUseCase(query, page) }
                        }.flow.cachedIn(viewModelScope)

                        _uiState.value = UIState.Success(SearchGameData(searchResultsFlow))
                    } catch (e: Exception) {
                        _uiState.value = UIState.Error(e)
                    }
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}