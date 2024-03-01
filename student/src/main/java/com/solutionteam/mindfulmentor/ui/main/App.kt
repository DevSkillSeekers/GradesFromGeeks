package com.solutionteam.mindfulmentor.ui.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.rememberNavController
import com.solutionteam.design_system.theme.GGTheme
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.RootNavGraph
import com.solutionteam.mindfulmentor.ui.profile.Language
import com.solutionteam.mindfulmentor.ui.profile.updateLanguage
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    val firstTime by viewModel.isFirstTimeOpenApp.collectAsState()
    val language by viewModel.language.collectAsState()
    val isLogin by viewModel.isLogin.collectAsState()
    val isDarkTheme by viewModel.theme.collectAsState()

    language?.let {
        updateLanguage(context = LocalContext.current, language = it.abbreviation)
        GGTheme(isDarkTheme = isDarkTheme ?: false) {
            CompositionLocalProvider(LocalLayoutDirection provides it.layoutDirection) {
                val navController = rememberNavController()

                if (isLogin) {
                    RootNavGraph(navController = navController, startDestination = Screen.Main)
                } else {
                    firstTime?.let { first ->
                        if (first) {
                            RootNavGraph(navController = navController, startDestination = Screen.OnBoarding)
                        } else {
                            RootNavGraph(navController = navController, startDestination = Screen.Welcome)
                        }

                    }
                }

            }
        }
    }
}
