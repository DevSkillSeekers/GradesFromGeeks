package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGPreferencesCard(
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = Theme.colors.card,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(16.dp)
            .noRippleEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            style = Theme.typography.bodyLarge,
            color = Theme.colors.primaryShadesDark,
            maxLines = 1
        )

        Icon(
            painter = painter,
            contentDescription = null,
            tint = Theme.colors.primaryShadesDark,
            modifier = Modifier.size(24.dp)
        )
    }
}
