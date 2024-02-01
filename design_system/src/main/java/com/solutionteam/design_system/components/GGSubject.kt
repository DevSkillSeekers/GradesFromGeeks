package com.solutionteam.design_system.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GGSubject(
    name: String,
    modifier: Modifier = Modifier,
    isWithBorder: Boolean = false,
    isClicked: Boolean = false,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .noRippleEffect { onClick() }
        .background(
            color = if (isWithBorder) Theme.colors.card else Theme.colors.secondary,
            shape = RoundedCornerShape(16.dp)
        )
        .border(
            width = if (isWithBorder) 1.dp else 0.dp,
            color = if (isClicked) Theme.colors.primary else Color.Transparent,
            shape = RoundedCornerShape(16.dp)
        )
        .padding(vertical = 24.dp, horizontal = 8.dp)
        .width(80.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = if (isWithBorder) R.drawable.ic_notebook else R.drawable.notebook_minimalistic),
            contentDescription = null,
            tint = if (isWithBorder) {
                if (isClicked) Theme.colors.primary else Theme.colors.ternaryShadesDark
            } else {
                Theme.colors.primary
            }
        )

        Text(
            text = name,
            style = if (isWithBorder)Theme.typography.labelMedium else Theme.typography.labelLarge,
            color = if (isWithBorder) {
                if (isClicked) Theme.colors.primary else Theme.colors.ternaryShadesDark
            } else {
                Theme.colors.primaryShadesDark
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.basicMarquee()
        )
    }
}
