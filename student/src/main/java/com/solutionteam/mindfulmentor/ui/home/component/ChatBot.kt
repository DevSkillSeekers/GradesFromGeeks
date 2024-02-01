package com.solutionteam.mindfulmentor.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@Composable
fun ChatBot(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .background(
                color = Theme.colors.card,
                shape = RoundedCornerShape(100.dp)
            )
            .noRippleEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.chat_bot),
            contentDescription = null,
            tint = Theme.colors.card,
            modifier = Modifier
                .background(
                    color = Theme.colors.primary,
                    shape = CircleShape
                )
                .size(48.dp)
                .clip(CircleShape)
                .padding(8.dp)

        )

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(id = R.string.welcome_chatbot),
            style = Theme.typography.bodyMedium,
            color = Theme.colors.ternaryShadesDark
        )
    }

}
