package dev.jamile.presentation.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function to display a message indicating the state of the search.
 *
 * @param message The message to be displayed.
 * @param modifier The modifier to be applied to the Text composable.
 */
@Composable
fun SearchStateMessage(
    message: String,
    modifier: Modifier = Modifier

) {
    Text(
        text = message,
        color = Color.White,
        fontSize = 16.sp,
        modifier = modifier.padding(16.dp)
    )
}