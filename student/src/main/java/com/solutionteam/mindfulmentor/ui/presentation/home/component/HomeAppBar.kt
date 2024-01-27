package com.solutionteam.mindfulmentor.ui.presentation.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    onNotificationClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier.shadow(1.dp),
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                style = Theme.typography.titleMedium,
                color = Theme.colors.primaryShadesDark
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .noRippleEffect { onNotificationClicked() }
                    .padding(horizontal = 8.dp),
                painter = painterResource(id = R.drawable.notification),
                contentDescription = null,
                tint = Theme.colors.primaryShadesDark
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Theme.colors.background
        )
    )

}
