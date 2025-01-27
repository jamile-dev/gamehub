package dev.jamile.presentation.ui.game_detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jamile.presentation.R
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function that displays the toolbar for the game detail screen.
 * The toolbar shows the game name and a back navigation icon.
 * The toolbar fades in and out based on the scroll state.
 *
 */
@Composable
fun GameToolbar(
    navController: NavController,
    contentAlpha: () -> Float,
    isFavorite: Boolean? = false,
    onFavoriteClick: () -> Unit,
    tint: Color,
    scale: Float,
    rotation: Float,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

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
            onClick = {
                coroutineScope.launch {
                    onFavoriteClick()
                }
            },
            modifier = modifier
                .background(ScreenBackgroundColor, shape = CircleShape)
                .size(32.dp)
                .scale(scale)
                .alpha(contentAlpha())
                .graphicsLayer {
                    rotationY = rotation
                }
        ) {
            Icon(
                imageVector = if (isFavorite == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (isFavorite == true) stringResource(R.string.remove_from_favorites) else stringResource(
                    R.string.add_to_favorites
                ),
                tint = tint
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