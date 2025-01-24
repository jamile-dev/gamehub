package dev.jamile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

/**
 * A composable that displays the rating inside a circular component with
 * a background color based on the rating value.
 * @param rating The rating to display.
 * @param modifier Optional [Modifier] to apply to [RatingIndicator]
 */
@Composable
fun RatingIndicator(rating: Double, modifier: Modifier = Modifier) {
    val color = when {
        rating >= 4F -> Color(0xFF4CAF50)
        rating >= 3F -> Color(0xFFFFEB3B)
        rating >= 2F -> Color(0xFFFF9800)
        else -> Color(0xFFF44336)
    }

    val backgroundColor = color.copy(alpha = 0.2f)
    val formattedRating = DecimalFormat("#.0").format(rating)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(backgroundColor, CircleShape)
            .border(1.dp, color, CircleShape)
            .size(30.dp)
            .padding(2.dp)
    ) {
        Text(
            text = formattedRating,
            color = Color.White,
            style = TextStyle(
                fontSize = 10.sp,
                shadow = Shadow(
                    color = Color.Black,
                    blurRadius = 4f
                )
            )
        )
    }
}