package com.solutionteam.mindfulmentor.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGBottomSheet
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.design_system.theme.mindfulMentorTypography
import com.solutionteam.mindfulmentor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeBottomSheet(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onThemeChanged: (Boolean) -> Unit
) {
    GGBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "Choose your theme",
            style = mindfulMentorTypography.titleSmall,
            color = Theme.colors.primaryShadesDark
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painterResource(id = R.drawable.theme_light),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .then(
                        if (!isDarkTheme) {
                            Modifier.border(
                                width = 2.dp,
                                color = Theme.colors.primary,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .noRippleEffect { onThemeChanged(false) },
                contentScale = ContentScale.FillWidth
            )

            Image(
                painterResource(id = R.drawable.frame_theme_dark),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .then(
                        if (isDarkTheme) {
                            Modifier.border(
                                width = 2.dp,
                                color = Theme.colors.primary,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .noRippleEffect { onThemeChanged(true) },
                contentScale = ContentScale.FillWidth
            )
        }
    }
}


