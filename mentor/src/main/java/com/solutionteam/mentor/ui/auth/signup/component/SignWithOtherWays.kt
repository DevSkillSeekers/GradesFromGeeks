package com.solutionteam.mentor.ui.auth.signup.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mentor.R
import com.solutionteam.mentor.ui.auth.composables.TextWithClick

@Composable
fun SignWithOtherWays() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "- Or -",
            style = Theme.typography.titleSmall,
            color = Theme.colors.ternaryShadesDark
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = ""
            )
        }
        TextWithClick(
            fullText = "Don't have an account? signUp",
            linkText = "signUp",
            onClick = {}
        )
    }
}