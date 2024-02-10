package com.solutionteam.design_system.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.solutionteam.design_system.theme.Gray_1
import com.solutionteam.design_system.theme.PrimaryLight
import com.solutionteam.design_system.theme.Theme

@Composable
fun <T> GGDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    hint: String,
    value:Int?,
    textStyle: TextStyle = Theme.typography.bodyLarge,
    notSetLabel: String? = null,
    items: List<T>,
    selectedIndex: Int = -1,
    colors: TextFieldColors = textFieldColorsDefault(),
    placeholder: String? = null,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        label?.let {
            Text(
                    text = label,
                    modifier = Modifier,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.secondaryShadesDark
            )
        }
        Box {
            OutlinedTextField(
                label = { Text("") },
                value = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: placeholder ?:"",
                enabled = enabled,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon =
                        if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                    Icon(icon, "")
                },
                onValueChange = { },
                readOnly = true,
                shape = RoundedCornerShape(100.dp),
                colors = colors,
                interactionSource = interactionSource,
                textStyle = Theme.typography.bodyMedium.copy(color = Theme.colors.primaryShadesDark)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .clickable(
                        enabled = enabled,
                        interactionSource = interactionSource,
                        indication = null
                    ) { expanded = true },
                color = Color.Transparent,
            ) {}
        }


        if (expanded) {
            Dialog(
                onDismissRequest = { expanded = false },
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                ) {
                    val listState = rememberLazyListState()
                    if (selectedIndex > -1) {
                        LaunchedEffect("ScrollToSelected") {
                            listState.scrollToItem(index = selectedIndex)
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                        if (notSetLabel != null) {
                            item {
                                LargeDropdownMenuItem(
                                    text = notSetLabel,
                                    selected = false,
                                    enabled = false,
                                    onClick = { },
                                )
                            }
                        }
                        itemsIndexed(items) { index, item ->
                            val selectedItem = index == selectedIndex
                            drawItem(
                                item,
                                selectedItem,
                                true
                            ) {
                                onItemSelected(index, item)
                                expanded = false
                            }

                            if (index < items.lastIndex) {
                                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> Theme.colors.primaryShadesDark
        selected -> Theme.colors.primaryShadesDark
        else -> Theme.colors.secondaryShadesDark
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
 fun textFieldColorsDefault(): TextFieldColors {
   return OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Theme.colors.disabled,
            cursorColor = Gray_1,
            errorCursorColor = PrimaryLight,
            focusedBorderColor = PrimaryLight.copy(alpha = 0.2f),
            unfocusedBorderColor = PrimaryLight.copy(alpha = 0.1f),
            errorBorderColor = PrimaryLight.copy(alpha = 0.5f),
    )
}
