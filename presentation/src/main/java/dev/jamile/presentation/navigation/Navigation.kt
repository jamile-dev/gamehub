package dev.jamile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.jamile.presentation.ui.favorites.FavoritesScreen
import dev.jamile.presentation.ui.gamedetail.GameDetailScreen
import dev.jamile.presentation.ui.home.HomeScreen
import dev.jamile.presentation.ui.search.SearchScreen
import dev.jamile.presentation.ui.splashscreen.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }

        composable(NavItem.Home.route) {
            HomeScreen(navigateToDetails = { gameId ->
                navController.navigate("details/$gameId")
            })
        }
        composable(NavItem.Search.route) {
            SearchScreen(navigateToDetails = { gameId ->
                navController.navigate("details/$gameId")
            })
        }
        composable(NavItem.Favorites.route) {
            FavoritesScreen(navigateToDetails = { gameId ->
                navController.navigate("details/$gameId")
            })
        }
        composable("details/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")
            GameDetailScreen(gameId = gameId ?: "", navController)
        }
    }
}
