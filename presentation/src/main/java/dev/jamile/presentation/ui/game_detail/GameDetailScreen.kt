package dev.jamile.presentation.ui.game_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jamile.domain.models.GameDetails
import dev.jamile.presentation.components.ErrorScreen
import dev.jamile.presentation.components.LoadingScreen
import dev.jamile.presentation.state.UIState

/**
 * Composable function that displays the game detail screen.
 * It fetches game details using the provided ViewModel and displays
 * the appropriate UI state (loading, success, or error).
 *
 * @param gameId The ID of the game to fetch details for.
 * @param navController The NavController for navigation.
 * @param viewModel The ViewModel to fetch game details.
 */
@Composable
fun GameDetailScreen(
    gameId: String,
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.fetchGameDetails(gameId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is UIState.Loading -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) { LoadingScreen() }

            is UIState.Success -> GameDetailContent(
                (uiState as UIState.Success<GameDetails>).data,
                navController
            )

            is UIState.Error -> ErrorScreen("", { })
        }
    }
}