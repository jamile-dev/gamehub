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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
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
 * @param modifier The modifier to be applied to the OutlinedTextField composable.
 */
@Composable
fun SearchTextField(
    query: MutableState<String>,
    searchJob: MutableState<Job?>,
    coroutineScope: CoroutineScope,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query.value,
        onValueChange = { newQuery ->
            query.value = newQuery
            searchJob.value?.cancel()
            searchJob.value = coroutineScope.launch {
                delay(700) // Debounce time
                if (query.value.isNotEmpty()) {
                    onSearch(query.value)
                }
                keyboardController?.hide()
            }
        },
        placeholder = { Text(text = "Search...", color = Color(0xFFB0B0B0)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFFB0B0B0)
            )
        },
        trailingIcon = {
            if (query.value.isNotEmpty()) {
                IconButton(onClick = { query.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = Color(0xFFB0B0B0)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (query.value.isNotEmpty()) {
                    onSearch(query.value)
                    keyboardController?.hide()
                }
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White
        ),
        singleLine = true,
        modifier = modifier
    )
}