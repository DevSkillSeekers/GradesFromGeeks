package com.solutionteam.design_system.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solutionteam.design_system.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    showDragHandle: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = rememberModalBottomSheetState(
            confirmValueChange = {
                onDismissRequest()
                true
            }),
        onDismissRequest = onDismissRequest,
        containerColor = Theme.colors.background,
        dragHandle = {
            if (showDragHandle)
                BottomSheetDefaults.DragHandle(color = Theme.colors.primary)
            else
                BottomSheetDefaults.HiddenShape
        },
        content = content
    )
}
