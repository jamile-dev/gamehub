import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.collectAsLazyPagingItems
import dev.jamile.presentation.R
import dev.jamile.presentation.components.ErrorScreen
import dev.jamile.presentation.components.GameListItem
import dev.jamile.presentation.components.LoadingScreen
import dev.jamile.presentation.state.UIState
import dev.jamile.presentation.ui.search.SearchGameData
import dev.jamile.presentation.ui.search.SearchStateMessage
import dev.jamile.presentation.ui.theme.CardBackgroundColor

/**
 * Composable function to display search results based on the UI state.
 *
 * @param uiState The current state of the UI, which can be Loading, Error, or Success.
 * @param navigateToDetails A lambda function to navigate to the details screen.
 * @param searchStarted A boolean indicating whether the search has started.
 */
@Composable
fun SearchResults(
    uiState: UIState<SearchGameData>,
    navigateToDetails: (String) -> Unit,
    searchStarted: Boolean
) {
    when (uiState) {
        is UIState.Loading -> {
            if (searchStarted) LoadingScreen()
        }

        is UIState.Error -> {
            val message = uiState.message
            ErrorScreen(message = message, onRetry = { /* Retry logic */ })
        }

        is UIState.Success -> {
            val games = uiState.data.games.collectAsLazyPagingItems()

            if (searchStarted && games.itemCount == 0 && games.loadState.refresh is NotLoading) {
                SearchStateMessage(message = "No results found")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    LazyColumn {
                        items(games.itemCount) { index ->
                            games[index]?.let {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = CardBackgroundColor
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    GameListItem(
                                        game = it,
                                        onClick = { navigateToDetails(it.id) }
                                    )
                                }
                            }
                        }
                        when (games.loadState.refresh) {
                            is Loading -> if (searchStarted) item { LoadingScreen() }
                            is Error -> {
                                val e = games.loadState.refresh as Error
                                item {
                                    ErrorScreen(
                                        message = "Error: ${e.error.localizedMessage}",
                                        onRetry = { games.retry() }
                                    )
                                }
                            }

                            else -> Unit
                        }
                        when (games.loadState.append) {
                            is Loading -> item { LoadingScreen() }

                            is NotLoading -> {
                                if (games.loadState.append.endOfPaginationReached) {
                                    item {
                                        SearchStateMessage(message = stringResource(R.string.no_more_items_to_load))
                                    }
                                }
                            }

                            else -> Unit
                        }
                    }
                }
            }
        }
    }
}