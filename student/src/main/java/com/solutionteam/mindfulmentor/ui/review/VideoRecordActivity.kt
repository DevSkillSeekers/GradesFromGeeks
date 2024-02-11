package com.solutionteam.mindfulmentor.ui.review

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.solutionteam.design_system.theme.GGTheme
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.main.navigation.Screen
import com.solutionteam.mindfulmentor.ui.main.navigation.graph.RootNavGraph


class VideoRecordActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()

            GGTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Theme.colors.background,
                    content = {
                        RootNavGraph(
                            navController = navController,
                            startDestination = Screen.Video
                        )
                    }
                )
            }
        }
    }
}