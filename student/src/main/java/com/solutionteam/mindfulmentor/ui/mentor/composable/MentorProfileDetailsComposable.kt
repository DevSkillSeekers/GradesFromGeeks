package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.solutionteam.design_system.R
import com.solutionteam.design_system.theme.Gold
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.mentor.MentorDetailsUIState

@Composable
fun MentorProfileDetails(
    state: MentorDetailsUIState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = state.imageUrl),
            contentDescription = "image_profile_without_shadow",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(shape = CircleShape, color = Color.White)
                .border(4.dp, Color.White, CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = state.name,
                style = Theme.typography.titleLarge,
                color = Theme.colors.background
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "star",
                    tint = Gold
                )
                Text(
                    text = "${state.rate}/10  -  (${100000})",
                    style = Theme.typography.labelLarge,
                    color = Theme.colors.background
                )
            }

            Text(
                text = state.university,
                style = Theme.typography.bodyMedium,
                color = Theme.colors.background
            )
        }
    }

}