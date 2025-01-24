package dev.jamile.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val icon: ImageVector, val title: String) {
    data object Home : NavItem("home", Icons.Filled.Home, "Home")
    data object Search : NavItem("search", Icons.Filled.Search, "Search")
    data object Favorites : NavItem("favorites", Icons.Filled.Favorite, "Favorites")
}