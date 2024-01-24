package com.solutionteam.mindfulmentor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.solutionteam.mindfulmentor.ui.presentation.App
import com.solutionteam.mindfulmentor.ui.theme.MindfulMentorTheme
import com.solutionteam.mindfulmentor.ui.theme.Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindfulMentorTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Theme.colors.backgroundColor
                ) {
                    App()
                }
            }
        }
    }
}