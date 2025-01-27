package dev.jamile.presentation.components

import androidx.compose.ui.graphics.Color

enum class Genre(
    val color: Color,
) {
    ACTION(Color.Red),
    ADVENTURE(Color.Blue),
    RPG(Color.Green),
    STRATEGY(Color.Magenta),
    SPORTS(Color.Cyan),
    PUZZLE(Color.Yellow),
    UNKNOWN(Color.Gray),
    ;

    companion object {
        fun fromString(genre: String): Genre = entries.find { it.name.equals(genre, ignoreCase = true) } ?: UNKNOWN
    }
}
