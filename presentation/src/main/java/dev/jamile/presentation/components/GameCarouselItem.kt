package dev.jamile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.jamile.domain.models.Game
import dev.jamile.presentation.R
import dev.jamile.presentation.ui.theme.AppTypography
import dev.jamile.presentation.ui.theme.CardBackgroundColor

/**
 * A composable that displays a game item in a carousel format.
 *
 * @param game The game to display.
 * @param onClick The callback to invoke when the item is clicked.
 * @param modifier Optional [Modifier] to apply to [GameCarouselItem]
 */
@Composable
fun GameCarouselItem(
    game: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .padding(8.dp)
                .width(220.dp)
                .padding(bottom = 12.dp)
                .height(240.dp)
                .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = game.imageUrl,
                contentDescription = stringResource(R.string.game_banner),
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .height(120.dp)
                        .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                PlatformLogos(
                    platforms = game.platforms ?: emptyList(),
                )
                Text(
                    text = game.name,
                    color = Color.White,
                    style = AppTypography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start).padding(8.dp),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier =
                    Modifier
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                GenreChips(game.genres)
                RatingIndicator(rating = game.rating, modifier = Modifier.size(32.dp))
            }
        }
    }
}
