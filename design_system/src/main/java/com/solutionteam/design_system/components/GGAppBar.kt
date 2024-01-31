package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.PrimaryShadesLight
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

@Composable
fun GGAppBar(
    title: @Composable() (() -> Unit?)? = null,
    trailingIcon: @Composable (RowScope.() -> Unit)? = null,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit),
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryShadesLight)
                .padding(15.dp)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        leadingIcon()
        title?.invoke()
        Spacer(modifier = Modifier.weight(1f))
        trailingIcon?.invoke(this)
    }

    Divider(
            thickness = 1.dp,
            color = Color.LightGray.copy(alpha = .3f),
            modifier = Modifier.fillMaxWidth()
    )
}