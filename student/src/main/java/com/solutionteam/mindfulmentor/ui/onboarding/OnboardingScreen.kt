package com.solutionteam.mindfulmentor.ui.onboarding

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.home.HomeUIEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun OnBoardingScreen(
    navigateTo: () -> Unit= {}
) {
    val pageState = rememberPagerState(
            initialPage = 0,
            pageCount = { sliderDataList.size })

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pageState.animateScrollToPage(
                    page = (pageState.currentPage + 1) % (if (pageState.pageCount == 0) 1 else pageState.pageCount),
                    animationSpec = tween(1000)
            )
        }
    }
    Column(
            modifier = Modifier
                .background(Theme.colors.background)
                .padding(vertical = 40.dp),

            ) {
        OnBoardingSlider(Modifier.weight(1f), pageState = pageState)

        ImageSliderIndicator(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 24.dp)
                    .padding(start = 24.dp),
                pagerState = pageState,
        )
        GGButton(title = stringResource(id = R.string.let_do_it), onClick =navigateTo,
                modifier = Modifier.padding(start = 24.dp),
                textPadding = PaddingValues(horizontal = 32.dp)
        )
    }
}

