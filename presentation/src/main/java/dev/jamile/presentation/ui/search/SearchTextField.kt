package dev.jamile.presentation.ui.search

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import dev.jamile.presentation.R
import dev.jamile.presentation.ui.theme.AppTypography
import dev.jamile.presentation.ui.theme.textColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Composable function to display a search text field with debounce.
 *
 * @param query The current search query.
 * @param searchJob The current search job.
 * @param coroutineScope The coroutine scope for launching debounce jobs.
 * @param onSearch A lambda function to perform the search.
 * @param modifier an optional modifier to be applied to the [OutlinedTextField] composable.
 */
@Composable
fun SearchTextField(
    query: MutableState<String>,
    searchJob: MutableState<Job?>,
    coroutineScope: CoroutineScope,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query.value,
        onValueChange = { newQuery ->
            query.value = newQuery
            searchJob.value?.cancel()
            searchJob.value =
                coroutineScope.launch {
                    delay(600) // Debounce time
                    if (query.value.isNotEmpty()) {
                        onSearch(query.value)
                    }
                    keyboardController?.hide()
                }
        },
        textStyle =
            AppTypography.titleLarge.copy(
                color = textColor,
            ),
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = AppTypography.titleLarge,
                color = textColor,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = textColor,
            )
        },
        trailingIcon = {
            if (query.value.isNotEmpty()) {
                IconButton(onClick = { query.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = textColor,
                    )
                }
            }
        },
        keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
        keyboardActions =
            KeyboardActions(
                onSearch = {
                    if (query.value.isNotEmpty()) {
                        onSearch(query.value)
                        keyboardController?.hide()
                    }
                },
            ),
        colors =
            TextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
            ),
        singleLine = true,
        modifier = modifier,
    )
}
