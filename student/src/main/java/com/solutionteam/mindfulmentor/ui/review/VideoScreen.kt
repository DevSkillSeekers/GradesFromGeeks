package com.solutionteam.mindfulmentor.ui.review

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun VideoScreen(
    viewModel: VideoViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()


    VideoContent(
        state = state,
        onBack = navigateBack,
    )

    val color = Theme.colors.primaryShadesDark
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            navigationBarColor = color,
            statusBarColor = color,
            isDarkIcon = false
        )
    }

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }

}


private fun onEffect(effect: VideoUIEffect?, context: Context) {

    when (effect) {
        VideoUIEffect.VideoError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun VideoContent(
    state: VideoUIState,
    onBack: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Theme.colors.primaryShadesDark),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = Theme.colors.background)
            } else {

                    VideoLayout(uri = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")

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
            .fillMaxSize(),
        factory = { it ->
            PlayerView(it).also {
                it.player = myStyledPlayerView.player
                it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
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