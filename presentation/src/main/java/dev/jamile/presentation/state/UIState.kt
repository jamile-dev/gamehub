package dev.jamile.presentation.state

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val exception: Throwable) : UIState<Nothing>()
}