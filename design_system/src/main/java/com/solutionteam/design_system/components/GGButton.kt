package com.solutionteam.design_system.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.DisabledColor
import com.solutionteam.design_system.theme.MainColor
import com.solutionteam.design_system.theme.MainFontColor
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = Theme.typography.normalFont,
    type: ContainerType = ContainerType.BUTTON,
    enabled: Boolean = true,
    textPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(16.dp),
    containerColor: Color = MainColor,
    contentColor: Color = MainFontColor,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    val buttonColor by animateColorAsState(
        if (enabled) containerColor else DisabledColor, label = ""
    )
    val height = if (type == ContainerType.CHIP) 36.dp else 48.dp

    Surface(
        modifier = modifier.height(height),
        onClick = onClick,
        color = buttonColor,
        contentColor = contentColor,
        shape = shape,
        enabled = enabled
    ) {
        Row(
            Modifier.defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = ButtonDefaults.MinHeight
            ),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = style.copy(color = contentColor),
                modifier = Modifier.padding(textPadding)
            )
        }
    }
}

enum class ContainerType {
    BUTTON,
    CHIP
}
