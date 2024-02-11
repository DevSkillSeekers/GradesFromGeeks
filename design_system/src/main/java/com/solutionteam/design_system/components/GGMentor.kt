package com.solutionteam.design_system.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Gold
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGMentor(
    name: String,
    subjectName: String = "",
    isForSubject: Boolean = false,
    rate: Double = 0.0,
    numberReviewers: Int = 0,
    profileUrl: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    imageSize: Int = 56,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Theme.colors.card,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(8.dp)
            .noRippleEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = profileUrl),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp)
                .clip(CircleShape)
                .size(imageSize.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = if (isForSubject) subjectName else name,
                style = Theme.typography.bodyLarge,
                color = if (isForSubject) Theme.colors.primary else Theme.colors.primaryShadesDark,
                maxLines = 1
            )

            if (isForSubject) {
                Text(
                    text = name,
                    style = Theme.typography.labelLarge,
                    color = Theme.colors.primaryShadesDark,
                    maxLines = 1
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = name,
                        tint = Gold
                    )
                    Text(
                        text = "${rate}/10  -  (${numberReviewers})",
                        color = Theme.colors.primary,
                        style = Theme.typography.labelLarge
                    )
                }
            }
        }
    }
}
