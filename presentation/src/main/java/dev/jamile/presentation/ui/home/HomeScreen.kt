package dev.jamile.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(navigateToDetails: (String) -> Unit) {
    Column {
        Text("HomeScreen")
    }
}