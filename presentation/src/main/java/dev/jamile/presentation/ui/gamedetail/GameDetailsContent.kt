package dev.jamile.presentation.ui.gamedetail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jamile.domain.models.GameDetails
import dev.jamile.presentation.components.GenreChips
import dev.jamile.presentation.components.PlatformLogos
import dev.jamile.presentation.components.RatingIndicator
import dev.jamile.presentation.ui.theme.AppTypography
import dev.jamile.presentation.ui.theme.Roboto
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor
import dev.jamile.presentation.utils.formatDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Composable function that displays the content of the game detail screen.
 * It includes a header image, game name, and description.
 *
 * @param gameDetails The details of the game to display.
 * @param navController The NavController for navigation.
 * @param viewModel The ViewModel to manage game details.
 */
@Composable
fun GameDetailContent(
    gameDetails: GameDetails,
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val gameScroller by remember {
        mutableStateOf(GameDetailsScroller(scrollState, Float.MIN_VALUE))
    }
    val transitionState = remember(gameScroller) { gameScroller.toolbarTransitionState }

    val transition = rememberTransition(transitionState, label = "")
    val contentAlpha =
        transition.animateFloat(
            transitionSpec = { spring(stiffness = Spring.StiffnessLow) },
            label = "",
        ) { toolbarTransitionState ->
            if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
        }
    val isFavorite by viewModel.isFavorite.collectAsState()

    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    val animatedTint by animateColorAsState(
        targetValue = if (isFavorite == true) Color.Red else Color.White,
        animationSpec = tween(durationMillis = 500),
    )

    LaunchedEffect(isFavorite) {
        if (isFavorite != null) {
            scale = 0.8f
            rotation = 360f * 2f

            launch {
                delay(500)
                scale = 1f
                rotation = 0f
            }
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(ScreenBackgroundColor),
    ) {
        Column(Modifier.verticalScroll(scrollState)) {
            GameDetailHeader(gameDetails.backgroundImage, scrollState)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = gameDetails.name,
                fontFamily = Roboto,
                style = AppTypography.headlineLarge,
                modifier = Modifier.padding(12.dp),
            )
            PlatformLogos(
                platforms = gameDetails.platforms ?: emptyList(),
                modifier = Modifier.padding(8.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RatingIndicator(
                    rating = gameDetails.rating ?: 0.0,
                    modifier = Modifier.padding(8.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                GenreChips(genres = gameDetails.genres, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(
                text = "Release Date: ${gameDetails.released?.formatDate()}",
                style = AppTypography.labelLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
            )
            Text(
                text = gameDetails.description ?: "",
                textAlign = TextAlign.Justify,
                style =
                    AppTypography.bodyLarge.copy(
                        letterSpacing = TextUnit(value = 1.5f, type = TextUnitType.Sp),
                    ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        GameToolbar(
            navController = navController,
            contentAlpha = { contentAlpha.value },
            tint = animatedTint,
            scale = scale,
            rotation = rotation,
            isFavorite = isFavorite,
            onFavoriteClick = {
                viewModel.toggleFavorite(gameDetails)
            },
        )
    }
}
