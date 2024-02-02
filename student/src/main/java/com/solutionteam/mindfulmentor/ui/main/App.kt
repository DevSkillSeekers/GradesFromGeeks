package com.solutionteam.mindfulmentor.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.RootNavGraph
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    val language by viewModel.language.collectAsState()

    CompositionLocalProvider(LocalLayoutDirection provides language.layoutDirection) {
        val navController = rememberNavController()
        RootNavGraph(navController = navController, startDestination = Screen.Main)
    }
}
