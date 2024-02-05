package com.solutionteam.mindfulmentor.ui.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@Composable
fun EmptySearchItem(
    modifier: Modifier = Modifier
) {
    Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(Theme.colors.secondary)
                    .padding(24.dp)
        ) {
            Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(id = R.drawable.search_icon),
                    tint = Theme.colors.primary,
                    contentDescription = null
            )
        }
        Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    text = stringResource(R.string.nothing_to_show),
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.primary
            )

            Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.empty_search_description),
                    style = Theme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = Theme.colors.primary
            )
        }
    }
}