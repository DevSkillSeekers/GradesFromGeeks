package com.solutionteam.mindfulmentor.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.theme.DisabledColor
import com.solutionteam.mindfulmentor.ui.theme.Gray_66
import com.solutionteam.mindfulmentor.ui.theme.MainColor
import com.solutionteam.mindfulmentor.ui.theme.MainFontColor
import com.solutionteam.mindfulmentor.ui.theme.Theme

@Composable
fun GGTextField(
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = Theme.typography.normalFont,
    textFieldModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    hint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    shapeRadius: Shape = RoundedCornerShape(100.dp),
    singleLine: Boolean = true,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 8.dp),
            style = style,
            color = MainFontColor
        )

        OutlinedTextField(
            modifier = textFieldModifier,
            value = text,
            placeholder = {
                Text(
                    hint,
                    style = style,
                    color = MainFontColor.copy(alpha = 0.6f)
                )
            },
            onValueChange = onValueChange,
            shape = shapeRadius,
            textStyle = style.copy(color = MainFontColor),
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
                disabledContainerColor = DisabledColor,
                cursorColor = Gray_66,
                errorCursorColor = MainColor,
                focusedBorderColor = MainColor.copy(alpha = 0.2f),
                unfocusedBorderColor = MainColor.copy(alpha = 0.1f),
                errorBorderColor = MainColor.copy(alpha = 0.5f),
            ),
        )

        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = Theme.typography.normalFont,
                color = MainColor
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
            tint = Gray_66
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