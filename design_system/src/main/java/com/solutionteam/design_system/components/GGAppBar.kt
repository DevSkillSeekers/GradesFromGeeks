package com.solutionteam.design_system.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGAppBar(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.colors.background,
    iconColor: Color = Theme.colors.primaryShadesDark,
    showNavigationIcon: Boolean = true,
    onBack: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier.shadow(1.dp),
        title = {
            Text(
                text = title,
                style = Theme.typography.titleMedium,
                color = Theme.colors.primaryShadesDark
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                Icon(
                    modifier = Modifier
                        .noRippleEffect(onBack)
                        .padding(horizontal = 8.dp),
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    tint = iconColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        )
    )
}
