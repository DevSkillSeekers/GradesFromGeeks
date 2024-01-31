package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.PrimaryShadesLight

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