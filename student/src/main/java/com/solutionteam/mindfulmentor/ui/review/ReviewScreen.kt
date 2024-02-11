package com.solutionteam.mindfulmentor.ui.review

import RatingStar
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.mentor.composable.ContentCountCard
import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReviewScreen(
    viewModel: ReviewViewModel = koinViewModel(),
    onNavigateTo: (ReviewUIEffect) -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    ReviewContent(
        state = state,
        onBack = navigateBack,
        navigateToSeeAll = { onNavigateTo(ReviewUIEffect.NavigateToSeeAll) },
        navigateToMentorProfile = { onNavigateTo(ReviewUIEffect.NavigateToMentorProfile) },
    )


    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }

}


private fun onEffect(effect: ReviewUIEffect?, context: Context) {

    when (effect) {
        ReviewUIEffect.ReviewError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun ReviewContent(
    state: ReviewUIState,
    onBack: () -> Unit,
    navigateToSeeAll: () -> Unit,
    navigateToMentorProfile: () -> Unit
) {

    Scaffold(
        topBar = {
            GGAppBar(title = "Review", onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Theme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Theme.colors.primaryShadesDark)
                        .height(250.dp)
                ) {
                    VideoLayout(uri = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            Theme.colors.background,
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                ) {

                    ContentCountCard(
                        contentCountList = listOf(
                            ContentCountUIState("10:00", "time"),
                            ContentCountUIState("Thr 23 May", "date"),
                            ContentCountUIState("32", "Attendance")
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )

                    GGMentor(
                        modifier = Modifier.padding(16.dp),
                        name = "Amnah Ali",
                        isForSubject = true,
                        subjectName = "Data Structure - lecture 1",
                        profileUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
                        onClick = {}
                    )

                    var rating by remember { mutableFloatStateOf(4.5f) }
                    RatingStar(
                        rating = rating,
                        maxRating = 5,
                        onStarClick = {
                            rating = it.toFloat()
                        }, false)

                }
            }
        }
    }
}

@Composable
fun VideoLayout(uri: String) {
    val context = LocalContext.current
    val myStyledPlayerView = StyledPlayerView(context)
    val myExoPlayer = ExoPlayer.Builder(LocalContext.current).build()
    myStyledPlayerView.player = myExoPlayer


    DisposableEffect(myExoPlayer) {
        onDispose { myExoPlayer.release() }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        factory = { it ->
            PlayerView(it).also {
                it.player = myStyledPlayerView.player
                it.resizeMode =  AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        },
        update = {
            myExoPlayer.setMediaItem(
                MediaItem.fromUri(
                    Uri.parse(uri)
                )
            )
            myExoPlayer.prepare()
            myExoPlayer.playWhenReady = true
            myExoPlayer.play()
        }
    )

}


@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}
