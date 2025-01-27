package dev.jamile.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.jamile.presentation.R

/**
 * A composable that displays platform logos based on the platform names.
 *
 * @param platforms The list of platforms to display.
 */
@Composable
fun PlatformLogos(platforms: List<String>, modifier: Modifier = Modifier) {
    val platformMap = mapOf(
        "PlayStation 4" to R.drawable.ic_ps5_logo,
        "Xbox Series S/X" to R.drawable.ic_xbox_logo,
        "PC" to R.drawable.ic_pc_logo,
        "Android" to R.drawable.ic_android_logo,
        "iOS" to R.drawable.ic_ios_logo,
    )
    Row(modifier = modifier.padding(horizontal = 8.dp)) {
        platforms.take(4).forEach { platform ->
            val logo = platformMap[platform]
            logo?.let { painterResource(id = it) }?.let {
                Icon(
                    painter = it,
                    tint = Color.White,
                    contentDescription = platform,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}