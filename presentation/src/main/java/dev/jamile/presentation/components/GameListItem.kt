package dev.jamile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.jamile.domain.models.Game
import dev.jamile.presentation.ui.theme.CardBackgroundColor

/**
 * A composable that displays a game item in a list format.
 *
 * @param game The game to display.
 * @param onClick The callback to invoke when the item is clicked.
 * @param modifier Optional [Modifier] to apply to [GameListItem]
 *
 */
@Composable
fun GameListItem(game: Game, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = game.imageUrl ?: "",
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(end = 8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(game.name, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                RatingIndicator(
                    rating = game.rating,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(game.releaseDate, color = Color.White)
            }
        }
    }
}