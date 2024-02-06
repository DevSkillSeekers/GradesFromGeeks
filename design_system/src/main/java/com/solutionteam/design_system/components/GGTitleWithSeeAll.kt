package com.solutionteam.design_system.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme


@Composable
fun GGTitleWithSeeAll(
    title: String,
    modifier: Modifier = Modifier,
    showSeeAll: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = title,
            color = Theme.colors.primaryShadesDark,
            style = Theme.typography.titleSmall,
        )

        Spacer(modifier = Modifier.weight(1f))

        if (showSeeAll) {
            Row(
                modifier = Modifier.noRippleEffect { onClick() },
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    style = Theme.typography.bodyMedium,
                    color = Theme.colors.ternaryShadesDark,
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleWithSeeAll() {
    GGTitleWithSeeAll(
        title = "Mentor",
        onClick = {}
    )
}
