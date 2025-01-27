package dev.jamile.presentation.ui.splashscreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jamile.presentation.R
import dev.jamile.presentation.ui.theme.ScreenBackgroundColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale by remember { mutableFloatStateOf(1f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var alpha by remember { mutableFloatStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
            ),
    )

    LaunchedEffect(key1 = true) {
        alpha = 1f
        offsetY = -100f

        delay(1500)

        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Surface(
        modifier =
            Modifier
                .fillMaxSize()
                .background(ScreenBackgroundColor),
        color = Color.Transparent,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash Screen Icon",
                modifier =
                    Modifier
                        .size(128.dp)
                        .scale(scale)
                        .graphicsLayer {
                            translationY = offsetY
                            rotationZ = rotation
                        },
            )
        }
    }
}
