package dev.jamile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jamile.presentation.ui.theme.AppTypography

/**
 * A composable that displays a list of genre chips.
 *
 * @param genres The list of genres to display.
 * @param modifier Optional [Modifier] to apply to the [GenreChips] container.
 */
@Composable
fun GenreChips(genres: List<String>?, modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(horizontal = 4.dp)) {
        genres?.filter { it.isNotBlank() }?.take(2)?.forEach { genre ->
            GenreChip(genre = genre)
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

/**
 * A composable that displays a single genre chip.
 *
 * @param genre The genre to display.
 */
@Composable
fun GenreChip(genre: String) {
    val color = when (genre.uppercase()) {
        "ACTION" -> Color(0xFFDF686C)
        "ADVENTURE" -> Color(0xFFFF9F1C)
        "RPG" -> Color(0xFFC8D6AF)
        "SHOOTER" -> Color(0xFFC8D6AF)
        "INDIE" -> Color(0xFFC8D6AF)
        else -> Color(0xFFF5EE9E)
    }
    val backgroundColor = color.copy(alpha = 0.4f)
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .border(1.dp, color, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = genre,
            color = Color.White,
            style = AppTypography.bodySmall,
            modifier = Modifier.padding(2.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}