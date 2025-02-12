package dev.jamile.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightPrimary = Color(0xFFFFD700) // Golden

// CustomColors
val CardBackgroundColor = Color(0xFF323645)
val ScreenBackgroundColor = Color(0xFF0D121E)
val textColor = Color(0xFF0D121E)

private val LightColorScheme =
    lightColorScheme(
        primary = LightPrimary,
        background = ScreenBackgroundColor,
    )

@Composable
fun GameHubTheme(content: @Composable () -> Unit) {
    val colors = LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content,
    )
}
