package com.solutionteam.mentor.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme

@Composable
fun MainScreen(
    nestedNavGraph: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit
) {

    Scaffold(
        bottomBar = bottomBar,
        contentWindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0)
    ) { paddingValues ->

        val systemUIController = rememberSystemUiController()
        val color = Theme.colors.background
        LaunchedEffect(true) {
            setStatusBarColor(
                systemUIController = systemUIController,
                navigationBarColor = color,
            )
        }

        ScreenBackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            nestedNavGraph.invoke()
        }
    }
}

@Composable
private fun ScreenBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val color = MaterialTheme.colorScheme.background

    Surface(
        color = if (color == Color.Unspecified) MaterialTheme.colorScheme.surface else color,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(
            LocalAbsoluteTonalElevation provides 0.dp
        ) {
            content()
        }
    }
}
