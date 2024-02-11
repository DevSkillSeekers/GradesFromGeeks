package com.solutionteam.mentor.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mentor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    name: String,
    profileUrl: String,
    modifier: Modifier = Modifier,
    onNotificationClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier.shadow(1.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = profileUrl),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .border(width = 2.dp, shape = CircleShape, color = Theme.colors.secondary)
                )

                Text(
                    text = "Hello $name",
                    style = Theme.typography.titleMedium,
                    color = Theme.colors.primaryShadesDark
                )
            }
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
