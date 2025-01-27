package dev.jamile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.jamile.domain.models.Game
import dev.jamile.presentation.ui.theme.AppTypography
import dev.jamile.presentation.ui.theme.CardBackgroundColor
import dev.jamile.presentation.utils.formatDate

/**
 * Composable function that displays a game item in a list.
 *
 * @param game The game details to display.
 * @param onClick A lambda function to handle item click.
 * @param modifier an optional modifier to be applied to the [GameListItem].
 */
@Composable
fun GameListItem(
    game: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
        ) {
            AsyncImage(
                model = game.imageUrl ?: "",
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier =
                    Modifier
                        .width(200.dp)
                        .fillMaxHeight()
                        .padding(end = 8.dp),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            ) {
                Text(
                    game.name,
                    fontWeight = FontWeight.Bold,
                    style = AppTypography.titleMedium,
                    modifier = Modifier.padding(2.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = game.releaseDate.formatDate(),
                    style = AppTypography.labelMedium,
                    modifier = Modifier.padding(2.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                GenreChips(game.genres)

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End,
                ) {
                    RatingIndicator(
                        rating = game.rating,
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
            }
        }
    }
}
