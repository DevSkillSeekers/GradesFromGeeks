package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGTextChipStyle(
    value: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.colors.secondary,
    textColor: Color = Theme.colors.primaryShadesDark,
    textStyle: TextStyle = Theme.typography.labelLarge,
    paddingValues: PaddingValues = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
) {
    Text(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(paddingValues),
        text = value,
        style = textStyle,
        color = textColor,
        maxLines = 1
    )
}
