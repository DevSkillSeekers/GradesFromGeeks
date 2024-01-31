package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGTextChipStyle(value: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(
                color = Theme.colors.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp),
        text = value,
        style = Theme.typography.labelLarge,
        color = Theme.colors.primaryShadesDark,
        maxLines = 1
    )
}
