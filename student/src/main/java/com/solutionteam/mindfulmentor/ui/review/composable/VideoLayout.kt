package com.solutionteam.mindfulmentor.ui.review.composable

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.solutionteam.mindfulmentor.utils.setScreenOrientation

@Composable
fun VideoLayout(exoPlayer: Player, onClick: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event -> lifecycle = event }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    AndroidView(
            factory = { context ->
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    setFullscreenButtonClickListener { isFullScreen ->
                        onClick(isFullScreen)
                        with(context) {
                            if (isFullScreen) {
                                setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                            } else {
                                setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            }
                        }
                    }
                }

            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }

                    else -> Unit
                }
            },
            modifier = modifier.fillMaxWidth(),
    )
}

