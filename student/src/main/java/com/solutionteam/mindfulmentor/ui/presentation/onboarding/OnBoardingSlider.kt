package com.solutionteam.mindfulmentor.ui.presentation.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingSlider(
    modifier: Modifier = Modifier,
    pageState: PagerState
) {
    HorizontalPager(
            state = pageState,
            modifier = modifier
    ) { page ->
        val sliderData by remember { mutableStateOf(sliderDataList[page]) }

        Column {

            Image(
                    modifier = Modifier
                        .padding(horizontal = 35.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = sliderData.image),
                    contentDescription = null
            )
            Row(modifier = Modifier.padding(start = 24.dp, top = 16.dp)) {
                Text(
                        text = stringResource(id = R.string.Grades_From_Geek),
                        style = Theme.typography.bodyLarge,
                        color = Theme.colors.primary,
                )
                Text(
                        text = "\uD83D\uDE4C",
                        style = Theme.typography.bodyLarge,
                        color = Theme.colors.primaryShadesDark,
                        modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                    text = stringResource(id = sliderData.title),
                    style = Theme.typography.titleLarge,
                    color = Theme.colors.primaryShadesDark,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 8.dp)
            )
            Text(
                    text = stringResource(id = sliderData.description),
                    style = Theme.typography.bodyMedium,
                    color = Theme.colors.ternaryShadesDark,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 20.dp)
            )

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
            modifier.wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val animatedColor by animateColorAsState(
                    label = "", targetValue =
            if (pagerState.currentPage == iteration) Theme.colors.primary else Theme.colors.secondary
            )
            Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(animatedColor)
                        .size(8.dp)
            )
        }
    }
}