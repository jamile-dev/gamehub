package dev.jamile.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.repository.FavoriteGamesRepository
import dev.jamile.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteGamesRepository: FavoriteGamesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<GameDetails>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<GameDetails>>> = _uiState

    fun loadFavoriteGames() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        try {
            _uiState.value = UIState.Success(favoriteGamesRepository.getAllFavoriteGames().first())
        } catch (e: Exception) {
            _uiState.value = UIState.Error(e)
        }
    }
}