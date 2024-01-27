package com.solutionteam.mindfulmentor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.FirebaseApp
import com.solutionteam.design_system.theme.MindfulMentorTheme
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.presentation.main.App

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContent {
            MindfulMentorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Theme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}
