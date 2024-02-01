package com.solutionteam.design_system.components

import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.solutionteam.design_system.theme.BackgroundLight

fun setStatusBarColor(
    color: Color = BackgroundLight,
    systemUIController: SystemUiController
) {
    systemUIController.setStatusBarColor(color)
}