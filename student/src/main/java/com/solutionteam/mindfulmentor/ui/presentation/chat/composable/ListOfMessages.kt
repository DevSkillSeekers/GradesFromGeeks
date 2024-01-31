package com.solutionteam.mindfulmentor.ui.presentation.chat.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.presentation.chat.ChatUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfChat(
    modifier: Modifier = Modifier,
    state: ChatUiState,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
            state = lazyListState,
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(
                items = state.messages,

        ) {
            MessageCard(it, Modifier.animateItemPlacement())
        }
    }
}
fun LazyListState.isScrolledToTheEnd(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 3
}