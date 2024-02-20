package com.solutionteam.mentor.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.rememberNavController
import com.solutionteam.design_system.theme.GGTheme
import com.solutionteam.mentor.ui.main.navigation.Screen
import com.solutionteam.mentor.ui.main.navigation.graph.RootNavGraph
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    val firstTime by viewModel.isFirstTimeOpenApp.collectAsState()
    val language by viewModel.language.collectAsState()
    val theme by viewModel.theme.collectAsState()

    GGTheme(isDarkTheme = theme) {
        CompositionLocalProvider(LocalLayoutDirection provides language.layoutDirection) {
            val navController = rememberNavController()
            firstTime?.let {
                if (it) {
                    RootNavGraph(
                        navController = navController, startDestination = Screen.SignUp
                    )
                } else {
                    RootNavGraph(navController = navController, startDestination = Screen.Main)
                }
            }
        }
    }

}
