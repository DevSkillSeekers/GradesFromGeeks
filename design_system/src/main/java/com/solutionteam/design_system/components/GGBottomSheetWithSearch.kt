package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGBottomSheetWithSearch(
    items: List<String>,
    onItemSelected: (String,Int) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    GGBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = Theme.colors.card
    ) {
        GGBottomSheetSearchComposable(items, onItemSelected)
    }

}

@Composable
private fun GGBottomSheetSearchComposable(
    items: List<String>,
    onItemSelected: (String,Int) -> Unit,
) {
    var searchText by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    val filteredItems = items.filter { it.contains(searchText.text, ignoreCase = true) }
    Box(
        modifier = Modifier
            .background(Theme.colors.card)
            .fillMaxHeight(0.7f)
    ) {

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
                    .navigationBarsPadding()
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                this.items(filteredItems.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onItemSelected(filteredItems[index], index)
                                        searchText = TextFieldValue(
                                                text = filteredItems[index],
                                                selection = TextRange(filteredItems[index].length)
                                        )
                                    }
                            )
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = filteredItems[index],
                            color = Theme.colors.primaryShadesDark,
                            style = Theme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                    Divider(color = Theme.colors.quaternaryShadesDark, thickness = 1.dp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetPreview() {
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val items = listOf(
        "ana",
        "dssdad",
        "dsadsa",
        "dsadsa",
        "dsadsa",
        "dsadsa",
        "sadmdsad",
        "dsadsad",
        "dsadsa",
        "sadmdsad",
        "dsadsad",
        "dsadsa",
        "sadmdsad",
        "dsadsad"
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
       GGButton(title = "open the sheet", onClick = { isSheetOpen = true })
        if(isSheetOpen){
            GGBottomSheetWithSearch(
                    items = items,
                    onItemSelected = { _, _ -> },
                    onDismissRequest = { isSheetOpen = false }
            )
        }
    }
}


