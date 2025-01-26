package dev.jamile.presentation.ui.search

import SearchResults
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor
import kotlinx.coroutines.Job


@Composable
fun SearchScreen(
    viewModel: SearchGameViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit
) {
    val query by viewModel.searchQuery.collectAsState()
    var searchStarted by remember { mutableStateOf(false) }
    val searchJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackgroundColor)
            .padding(8.dp)
    ) {
        SearchTextField(
            query = remember { mutableStateOf(query) },
            searchJob = remember { mutableStateOf(searchJob) },
            coroutineScope = coroutineScope,
            onSearch = {
                searchStarted = true
                viewModel.onSearchQueryChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        SearchResults(
            uiState = uiState,
            navigateToDetails = navigateToDetails,
            searchStarted = searchStarted
        )
    }
}