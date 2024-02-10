package com.solutionteam.design_system.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Gray_1
import com.solutionteam.design_system.theme.PrimaryLight
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGToggleBottomSheetButton(
    value: String,
    onValueChanged: () -> Unit,
    isOpen: Boolean,
    onToggle: () -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = true,
    colors: TextFieldColors = textFieldColorsDefault(),
    label: String? = null,
    labelStyle: TextStyle = Theme.typography.bodyLarge,
    textStyle: TextStyle = Theme.typography.bodyLarge,
    textFieldModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    shapeRadius: Shape = RoundedCornerShape(100.dp),
    singleLine: Boolean = true,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        label?.let {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 8.dp),
                style = labelStyle,
                color = Theme.colors.secondaryShadesDark
            )
        }
        Box {
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChanged() },
                readOnly = readOnly,
                placeholder = {
                    Text(
                        hint,
                        style = textStyle,
                        color = Theme.colors.primaryShadesDark.copy(alpha = 0.6f)
                    )
                },
                shape = shapeRadius,
                textStyle = textStyle.copy(color = Theme.colors.primaryShadesDark),
                singleLine = singleLine,
                isError = isError,
                colors = colors,
                trailingIcon = {
                    IconButton(
                        onClick = onToggle,
                        modifier = Modifier
                            .size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Toggle bottom sheet"
                        )
                    }
                },
                modifier = textFieldModifier
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .clickable(
                        enabled = true,
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onToggle()
                    },
                color = Color.Transparent,
            ) {}
        }
    }
}