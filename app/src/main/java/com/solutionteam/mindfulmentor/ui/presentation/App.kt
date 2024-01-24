package com.solutionteam.mindfulmentor.ui.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.solutionteam.mindfulmentor.ui.presentation.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App() {
    HomeScreen()
}