package dev.jamile.presentation.ui.game_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor

/**
 * Composable function that displays the toolbar for the game detail screen.
 * The toolbar shows the game name and a back navigation icon.
 * The toolbar fades in and out based on the scroll state.
 *
 * @param toolbarState The current state of the toolbar (hidden or shown).
 * @param gameName The name of the game to display in the toolbar.
 * @param navController The NavController for navigation.
 * @param toolbarAlpha The alpha value for the toolbar.
 * @param contentAlpha The alpha value for the content.
 */
@Composable
fun GameToolbar(
    navController: NavController,
    contentAlpha: () -> Float,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .background(ScreenBackgroundColor, shape = CircleShape)
                .size(32.dp)
                .alpha(contentAlpha())
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .background(ScreenBackgroundColor, shape = CircleShape)
                .size(32.dp)
                .alpha(contentAlpha())
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }
    }
}

/**
 * Enum class representing the state of the toolbar (hidden or shown).
 */
enum class ToolbarState { HIDDEN, SHOWN }

/**
 * Extension property to check if the toolbar is shown.
 */
val ToolbarState.isShown
    get() = this == ToolbarState.SHOWN