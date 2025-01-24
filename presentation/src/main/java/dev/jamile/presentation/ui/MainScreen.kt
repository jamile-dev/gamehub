package dev.jamile.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.jamile.presentation.navigation.NavItem
import dev.jamile.presentation.navigation.Navigation
import dev.jamile.presentation.ui.theme.GameHubTheme
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    GameHubTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) },
            content = { paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Navigation(navController)
                }
            }
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavItem.Home,
        NavItem.Search,
        NavItem.Favorites
    )
    BottomNavigation(
        backgroundColor = ScreenBackgroundColor,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) Color.DarkGray else Color.White
                    )
                },
                label = {
                    Text(
                        item.title,
                        color = if (isSelected) Color.DarkGray else Color.White
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
