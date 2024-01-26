package com.solutionteam.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf


private val LocalColorScheme = staticCompositionLocalOf { LightColors }
private val LocalTypography = staticCompositionLocalOf { MindfulMentorTypography() }


object Theme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: MindfulMentorTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}
