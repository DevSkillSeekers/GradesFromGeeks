package com.solutionteam.mindfulmentor.di

import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val VideoPlayerModule = module {
    factory<Player> {
        ExoPlayer.Builder(androidApplication().applicationContext)
            .setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
            .build()
    }
}
