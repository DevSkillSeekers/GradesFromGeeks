package com.solutionteam.design_system.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val mindfulMentorTypography = MindfulMentorTypography(
        titleLarge = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                lineHeight = 38.sp
        ),
        titleMedium = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 32.sp
        ),
        titleSmall = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 28.sp
        ),
        bodyLarge =TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 22.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp
        ),
        labelLarge = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 20.sp
        ),
        labelMedium =TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 15.sp
        ),
)

data class MindfulMentorTypography(
    val titleLarge: TextStyle = TextStyle(),
    val titleMedium: TextStyle = TextStyle(),
    val titleSmall: TextStyle = TextStyle(),
    val bodyLarge: TextStyle = TextStyle(),
    val bodyMedium: TextStyle = TextStyle(),
    val labelLarge: TextStyle = TextStyle(),
    val labelMedium: TextStyle = TextStyle(),
)
