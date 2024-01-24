package com.solutionteam.mindfulmentor.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val mindfulMentorTypography = MindfulMentorTypography(
        mainFontSemiBold = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                lineHeight = 38.sp
        ),
        normalFont = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 16.sp
        ),
        navBarNormal = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 12.sp
        ),
        navBarActive = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 14.sp
        ),
        card = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 14.sp
        ),
        ctaBig = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 18.sp
        ),
        ctaSmall = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 16.sp
        ),
        mainFontMedium = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                lineHeight = 38.sp
        ),
        specialCaseFont = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 18.sp
        ),
        pop2 = TextStyle(
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 14.sp
        ),
)

data class MindfulMentorTypography(
    val mainFontSemiBold: TextStyle = TextStyle(),
    val normalFont: TextStyle = TextStyle(),
    val navBarNormal: TextStyle = TextStyle(),
    val navBarActive: TextStyle = TextStyle(),
    val card: TextStyle = TextStyle(),
    val ctaBig: TextStyle = TextStyle(),
    val ctaSmall: TextStyle = TextStyle(),
    val mainFontMedium: TextStyle = TextStyle(),
    val specialCaseFont: TextStyle = TextStyle(),
    val pop2: TextStyle = TextStyle(),
)
