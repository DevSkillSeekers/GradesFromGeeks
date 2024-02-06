package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGBottomSheetSearchComposable(
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var searchText by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    val filteredItems = items.filter { it.contains(searchText.text, ignoreCase = true) }
    Box(modifier = Modifier.background(Theme.colors.card)) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Search, "search") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Handle action */ }),
                shape = RoundedCornerShape(100.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledContainerColor = Theme.colors.background,
                    unfocusedContainerColor = Theme.colors.background,
                    focusedContainerColor = Theme.colors.background,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            filteredItems.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onItemSelected(item)
                                searchText = TextFieldValue(text = item, selection = TextRange(item.length))
                            }
                        )
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = item,
                        color = Theme.colors.primaryShadesDark,
                        style = Theme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Divider(color = Theme.colors.quaternaryShadesDark, thickness = 1.dp)
            }
        }
    }
}
