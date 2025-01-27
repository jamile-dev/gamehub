package dev.jamile.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.jamile.presentation.R
import dev.jamile.presentation.ui.theme.AppTypography

/**
 * Composable function that displays an error screen with a retry button.
 *
 * @param message The error message to display.
 * @param onRetry A lambda function to handle retry button click.
 */
@Composable
fun ErrorScreen(
    message: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_rip),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message ?: stringResource(R.string.unkown_error),
            style = AppTypography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}
