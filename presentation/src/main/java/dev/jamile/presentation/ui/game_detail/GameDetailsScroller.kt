package dev.jamile.presentation.ui.game_detail

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

private val HeaderTransitionOffset = 190.dp

/**
 * Data class that manages the scroll state and toolbar transition state
 * for the game detail screen.
 *
 * @param scrollState The scroll state of the game detail screen.
 * @param namePosition The position of the game name in the screen.
 */
data class GameDetailsScroller(
    val scrollState: ScrollState,
    val namePosition: Float,
) {
    val toolbarTransitionState = MutableTransitionState(ToolbarState.HIDDEN)

    /**
     * Determines the toolbar state based on the scroll position and name position.
     *
     * @param density The density of the screen to calculate the transition offset.
     * @return The current state of the toolbar (shown or hidden).
     */
    fun getToolbarState(density: Density): ToolbarState =
        if (namePosition > 1f &&
            scrollState.value > (namePosition - getTransitionOffset(density))
        ) {
            toolbarTransitionState.targetState = ToolbarState.SHOWN
            ToolbarState.SHOWN
        } else {
            toolbarTransitionState.targetState = ToolbarState.HIDDEN
            ToolbarState.HIDDEN
        }

    /**
     * Calculates the transition offset based on the screen density.
     *
     * @param density The density of the screen.
     * @return The transition offset in pixels.
     */
    private fun getTransitionOffset(density: Density): Float =
        with(density) {
            HeaderTransitionOffset.toPx()
        }
}
