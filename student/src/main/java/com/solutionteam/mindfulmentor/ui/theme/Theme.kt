package com.solutionteam.mindfulmentor.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.theme.DarkColors
import com.solutionteam.design_system.theme.LightColors
import com.solutionteam.design_system.theme.MindfulMentorTypography
import com.solutionteam.design_system.theme.mindfulMentorTypography


private val LocalColorScheme = staticCompositionLocalOf { LightColors }
private val LocalTypography = staticCompositionLocalOf { MindfulMentorTypography() }
val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided.")
}

@Composable
fun MindfulMentorTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColors else LightColors
    val systemUiController = rememberSystemUiController()
    val darkIcons = !isDarkTheme

    DisposableEffect(systemUiController, darkIcons) {
        systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true,
                isNavigationBarContrastEnforced = false
        )
        onDispose {}
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    CompositionLocalProvider(
            LocalNavigationProvider provides rememberNavController(),
            LocalColorScheme provides colorScheme,
            LocalTypography provides mindfulMentorTypography,
    ) {
        content()
    }
}
