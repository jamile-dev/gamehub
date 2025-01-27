package dev.jamile.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.jamile.presentation.R
import dev.jamile.presentation.components.ErrorScreen
import dev.jamile.presentation.components.GameCarouselItem
import dev.jamile.presentation.components.GameListItem
import dev.jamile.presentation.components.LoadingScreen
import dev.jamile.presentation.state.UIState
import dev.jamile.presentation.ui.theme.AppTypography
import dev.jamile.presentation.ui.theme.LightPrimary
import dev.jamile.presentation.ui.theme.Roboto
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadGames() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GameHub",
                        color = LightPrimary,
                        style = AppTypography.headlineLarge,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.ExtraBold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ScreenBackgroundColor
                ),
                modifier = Modifier.padding(vertical = 4.dp),
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ScreenBackgroundColor)
                    .padding(8.dp)
            ) {
                when (uiState) {
                    is UIState.Loading -> {
                        LoadingScreen()
                    }

                    is UIState.Error -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ErrorScreen(message = "Error", onRetry = { viewModel.loadGames() })
                        }
                    }

                    is UIState.Success -> {
                        val gamesData = (uiState as UIState.Success).data
                        val popularGames = gamesData.popularGames.collectAsLazyPagingItems()
                        val recentGames = gamesData.recentGames.collectAsLazyPagingItems()

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            Text(
                                stringResource(R.string.popular_games),
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Roboto
                            )
                            LazyRow {
                                items(popularGames.itemCount) { index ->
                                    popularGames[index]?.let {
                                        GameCarouselItem(
                                            game = it,
                                            onClick = { navigateToDetails(it.id) })
                                    }
                                }
                                when (popularGames.loadState.refresh) {
                                    is LoadState.Loading -> item { LoadingScreen() }
                                    is LoadState.Error -> {
                                        val e = popularGames.loadState.refresh as LoadState.Error
                                        item {
                                            ErrorScreen(
                                                message = stringResource(
                                                    R.string.error,
                                                    e.error.localizedMessage ?: ""
                                                ),
                                                onRetry = { popularGames.retry() })
                                        }
                                    }

                                    else -> Unit
                                }
                                when (popularGames.loadState.append) {
                                    is LoadState.Loading -> item { LoadingScreen() }
                                    is LoadState.Error -> {
                                        val e = popularGames.loadState.append as LoadState.Error
                                        item {
                                            ErrorScreen(
                                                message = stringResource(
                                                    R.string.error,
                                                    e.error.localizedMessage ?: ""
                                                ),
                                                onRetry = { popularGames.retry() })
                                        }
                                    }

                                    else -> Unit
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                stringResource(R.string.best_of_year),
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Roboto
                            )
                            LazyColumn {
                                items(recentGames.itemCount) { index ->
                                    recentGames[index]?.let {
                                        GameListItem(
                                            game = it,
                                            onClick = { navigateToDetails(it.id) })
                                    }
                                }
                                when (recentGames.loadState.refresh) {
                                    is LoadState.Loading -> item { LoadingScreen() }
                                    is LoadState.Error -> {
                                        val e = recentGames.loadState.refresh as LoadState.Error
                                        item {
                                            ErrorScreen(
                                                message = stringResource(
                                                    R.string.error,
                                                    e.error.localizedMessage ?: ""
                                                ),
                                                onRetry = { recentGames.retry() })
                                        }
                                    }

                                    else -> Unit
                                }
                                when (recentGames.loadState.append) {
                                    is LoadState.Loading -> item { LoadingScreen() }
                                    is LoadState.Error -> {
                                        val e = recentGames.loadState.append as LoadState.Error
                                        item {
                                            ErrorScreen(
                                                message = stringResource(
                                                    R.string.error,
                                                    e.error.localizedMessage ?: ""
                                                ),
                                                onRetry = { recentGames.retry() })
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
    )
}