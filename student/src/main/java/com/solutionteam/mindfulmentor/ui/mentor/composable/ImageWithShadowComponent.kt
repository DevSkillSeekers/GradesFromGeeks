package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.solutionteam.design_system.R
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme

@Composable
fun ImageWithShadowComponent(
    modifier: Modifier,
    imageUrl: String,
    onBack: () -> Unit
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "image_profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.primary.copy(alpha = 0.9f))
        )

        Icon(
            modifier = Modifier
                .noRippleEffect { onBack() }
                .padding(16.dp)
                .size(24.dp),
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "back",
            tint = Theme.colors.background
        )
    }
}