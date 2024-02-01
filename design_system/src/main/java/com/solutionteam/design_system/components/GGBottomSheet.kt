package com.solutionteam.design_system.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solutionteam.design_system.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGBottomSheet(
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = Theme.colors.background,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Theme.colors.primary) },
        content = content
    )
}
