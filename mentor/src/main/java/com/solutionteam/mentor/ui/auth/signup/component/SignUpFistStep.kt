package com.solutionteam.mentor.ui.auth.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGTextField
import com.solutionteam.mentor.ui.auth.signup.SignUpUiState

@Composable
fun SignUpFistStep(
    state: SignUpUiState,
    onChangeEmail: (String) -> Unit,
    onChangeUserName: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onClickContinue: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        GGTextField(label = "Email", text = state.email, onValueChange = onChangeEmail)
        GGTextField(
            label = "User Name",
            text = state.userName,
            onValueChange = onChangeUserName
        )
        GGTextField(
            label = "Your PassWord",
            text = state.password, onValueChange = onChangePassword,
            keyboardType = KeyboardType.Password
        )
        GGButton(
            title = "Continue",
            onClick = onClickContinue,
            modifier = Modifier.fillMaxWidth()
        )
        SignWithOtherWays()
    }
}
