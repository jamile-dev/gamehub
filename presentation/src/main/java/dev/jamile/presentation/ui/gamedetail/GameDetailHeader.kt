package dev.jamile.presentation.ui.gamedetail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

/**
 * Composable function that displays the header image of the game detail screen.
 * The image fades out as the user scrolls down.
 *
 * @param imageUrl The URL of the image to display.
 * @param scrollState The scroll state to determine the image alpha.
 * @param modifier The modifier to be applied to the header image.
 */
@Composable
fun GameDetailHeader(
    imageUrl: String?,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    val imageHeight = 300.dp
    val imageAlpha = (1f - (scrollState.value / imageHeight.value)).coerceIn(0f, 1f)

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(imageHeight)
                .alpha(imageAlpha),
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
