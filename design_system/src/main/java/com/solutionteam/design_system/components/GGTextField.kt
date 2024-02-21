package com.solutionteam.design_system.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.theme.Gray_1
import com.solutionteam.design_system.theme.PrimaryLight
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    labelStyle: TextStyle = Theme.typography.bodyLarge,
    textStyle: TextStyle = Theme.typography.bodyLarge,
    textFieldModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    hint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    shapeRadius: Shape = RoundedCornerShape(100.dp),
    singleLine: Boolean = true,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    focusedBorderColor: Color = PrimaryLight.copy(alpha = 0.2f),
    hintTextAlign: TextAlign = TextAlign.Justify
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        label?.let {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 8.dp),
                style = labelStyle,
                color = Theme.colors.secondaryShadesDark
            )
        }

        OutlinedTextField(
            modifier = textFieldModifier,
            value = text,
            placeholder = {
                Text(
                    hint,
                    style = textStyle,
                    textAlign = hintTextAlign,
                    color = Theme.colors.primaryShadesDark.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onValueChange = onValueChange,
            shape = shapeRadius,
            textStyle = textStyle.copy(color = Theme.colors.primaryShadesDark),
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = if (keyboardType == KeyboardType.Password) {
                { TrailingIcon(showPassword) { showPassword = !showPassword } }
            } else null,
            visualTransformation = BpVisualTransformation(keyboardType, showPassword),
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Theme.colors.disabled,
                cursorColor = Gray_1,
                errorCursorColor = PrimaryLight,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = PrimaryLight.copy(alpha = 0.1f),
                errorBorderColor = PrimaryLight.copy(alpha = 0.5f),
            ),
        )

        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = Theme.typography.titleMedium,
                color = PrimaryLight
            )
        }
    }
}

@Composable
private fun TrailingIcon(
    showPassword: Boolean,
    togglePasswordVisibility: () -> Unit
) {
    IconButton(onClick = { togglePasswordVisibility() }) {
        Icon(
            painter = painterResource(
                id = if (showPassword) {
                    R.drawable.ic_pass_eye
                } else {
                    R.drawable.ic_hide_pass
                }
            ),
            contentDescription = if (showPassword) "Show Password" else "Hide Password",
            tint = Gray_1
        )
    }

}

@Composable
private fun BpVisualTransformation(
    keyboardType: KeyboardType,
    showPassword: Boolean
): VisualTransformation {
    return if (showPassword || keyboardType != KeyboardType.Password && keyboardType != KeyboardType.NumberPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}
