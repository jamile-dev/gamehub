package dev.jamile.presentation.ui.game_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jamile.domain.models.GameDetails
import dev.jamile.domain.models.Result
import dev.jamile.domain.usecases.GetGameDetailsUseCase
import dev.jamile.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<GameDetails>>(UIState.Loading)
    val uiState: StateFlow<UIState<GameDetails>> = _uiState

    fun fetchGameDetails(gameId: String) {
        viewModelScope.launch {
            when (val result = getGameDetailsUseCase.execute(gameId)) {
                is Result.Success -> _uiState.value = UIState.Success(result.data)
                is Result.Error -> _uiState.value =
                    UIState.Error(Throwable(result.exception.message ?: "Unknown error"))

            }
        }
    }
}