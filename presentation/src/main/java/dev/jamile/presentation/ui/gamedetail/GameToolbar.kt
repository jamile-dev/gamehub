package dev.jamile.presentation.ui.gamedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import kotlinx.coroutines.launch

/**
 * Composable function that displays the toolbar for the game detail screen.
 * It includes a back button, favorite button, and title.
 *
 * @param navController The NavController for navigation.
 * @param contentAlpha A lambda function to get the alpha value for the content.
 * @param tint The color tint for the favorite button.
 * @param scale The scale value for the favorite button animation.
 * @param rotation The rotation value for the favorite button animation.
 * @param isFavorite A boolean indicating if the game is marked as favorite.
 * @param onFavoriteClick A lambda function to handle favorite button click.
 * @param modifier Optional [Modifier] to apply to the [GameToolbar] container.
 */
@Composable
fun GameToolbar(
    navController: NavController,
    contentAlpha: () -> Float,
    tint: Color,
    scale: Float,
    rotation: Float,
    isFavorite: Boolean? = false,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier =
                Modifier
                    .background(ScreenBackgroundColor, shape = CircleShape)
                    .size(32.dp)
                    .clickable(
                        onClick = { },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(),
                    ).alpha(contentAlpha()),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
            )
        }
        IconButton(
            onClick = {
                coroutineScope.launch {
                    onFavoriteClick()
                }
            },
            modifier =
                modifier
                    .background(ScreenBackgroundColor, shape = CircleShape)
                    .size(32.dp)
                    .scale(scale)
                    .alpha(contentAlpha())
                    .graphicsLayer {
                        rotationY = rotation
                    },
        ) {
            Icon(
                imageVector = if (isFavorite == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription =
                    if (isFavorite == true) {
                        stringResource(R.string.remove_from_favorites)
                    } else {
                        stringResource(
                            R.string.add_to_favorites,
                        )
                    },
                tint = tint,
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
