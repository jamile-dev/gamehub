package dev.jamile.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.jamile.presentation.R

/**
 * A composable that displays platform logos based on the platform names.
 *
 * @param platforms The list of platforms to display.
 */
@Composable
fun PlatformLogos(platforms: List<String>) {
    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
        platforms.forEach { platform ->
            val logo = when (platform) {
                "PS5" -> R.drawable.ic_ps5_logo
                "Xbox" -> R.drawable.ic_xbox_logo
                "PC" -> R.drawable.ic_pc_logo
                "Android" -> R.drawable.ic_android_logo
                "iOS" -> R.drawable.ic_ios_logo
                else -> null
            }
            logo?.let { painterResource(id = it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = platform,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
            }
        }
    }
}