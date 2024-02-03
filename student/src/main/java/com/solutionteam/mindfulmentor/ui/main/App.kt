package com.solutionteam.mindfulmentor.ui.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.solutionteam.mindfulmentor.ui.home.HomeViewModel
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.RootNavGraph
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App(
    viewModel: MainViewModel = koinViewModel(),
) {
    val firstTime by viewModel.isFirstTimeOpenApp.collectAsState()

    val navController = rememberNavController()
    firstTime?.let {
        if (it) {
            RootNavGraph(navController = navController, startDestination = Screen.OnBoarding)
        } else {
            RootNavGraph(navController = navController, startDestination = Screen.Main)
        }
    }
}
