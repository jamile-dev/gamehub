package dev.jamile.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFFFD700) // Golden

// CustomColors
val CardBackgroundColor = Color(0xFF202020)
val ScreenBackgroundColor = Color(0xFF161616)

private val LightColorScheme = lightColorScheme(
    primary = primary,
)

@Composable
fun GameHubTheme(content: @Composable () -> Unit) {
    val colors = LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}