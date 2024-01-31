package com.solutionteam.design_system.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GGAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
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
            Icon(
                modifier = Modifier
                    .noRippleEffect { onBack() }
                    .padding(horizontal = 8.dp),
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                tint = Theme.colors.primaryShadesDark
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Theme.colors.background
        )
    )
}
