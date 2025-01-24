package dev.jamile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.jamile.domain.models.GameDetails

/**
 * A composable that displays detailed information about a game in a bottom sheet format.
 *
 * @param game The game details to display.
 * @param modifier Optional [Modifier] to apply to [GameDetailBottomSheet]
 */
@Composable
fun GameDetailBottomSheet(game: GameDetails, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = "Game Background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(game.name, style = MaterialTheme.typography.titleLarge)
            Text("Rating: ${game.rating}")
            Text("Metascore: ${game.metacritic}")
            Text("Release Date: ${game.released}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(game.description ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Screenshots", style = MaterialTheme.typography.labelSmall)
        }
    }
}
