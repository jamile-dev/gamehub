package dev.jamile.presentation.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jamile.domain.models.GameDetails
import dev.jamile.presentation.components.ErrorScreen
import dev.jamile.presentation.components.GameListItem
import dev.jamile.presentation.components.LoadingScreen
import dev.jamile.presentation.state.UIState

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.loadFavoriteGames()
    }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        when (uiState) {
            is UIState.Loading -> LoadingScreen(Modifier.fillMaxSize())
            is UIState.Success -> {
                val favoriteGames = (uiState as UIState.Success<List<GameDetails>>).data
                FavoritesList(favoriteGames, navigateToDetails)
            }

            is UIState.Error -> {
                ErrorScreen(
                    message = (uiState as UIState.Error).exception.message,
                    onRetry = { viewModel.loadFavoriteGames() },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun FavoritesList(games: List<GameDetails>, navigateToDetails: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (games.isEmpty()) {
            item {
                Text("No favorite games yet.")
            }
        } else {
            items(games) { game ->
                GameListItem(
                    game = game.toGame(),
                    onClick = { navigateToDetails(game.id.toString()) })
            }
        }
    }
}