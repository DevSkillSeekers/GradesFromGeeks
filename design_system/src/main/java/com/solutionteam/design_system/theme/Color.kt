package com.solutionteam.design_system.theme

import androidx.compose.ui.graphics.Color

val PrimaryLight = Color(0xFF4B69D0)
val SecondaryLight = Color(0xFFDDEDFF)
val BackgroundLight = Color(0xFFF6F7F7)
val CardLight = Color(0xFFFFFFFF)

val SecondaryDark = Color(0x9904030A)
val BackgroundDark = Color(0x6104030A)
val CardDark = Color(0xFF1C1C1E)

val PrimaryShadesLight = Color(0xDEFFFFFF)
val SecondaryShadesLight = Color(0x99FFFFFF)
val TernaryShadesLight = Color(0x61FFFFFF)
val QuaternaryShadesLight = Color(0x29FFFFFF)

val PrimaryShadesDark = Color(0xFF04030A)
val SecondaryShadesDark = Color(0x9904030A)
val TernaryShadesDark = Color(0x6104030A)
val QuaternaryShadesDark = Color(0x2904030A)

val Green = Color(0xFF34BC83)
val Red = Color(0xFFD65656)
val Green_Light = Color(0xFFDFF6E4)

val Gray_1 = Color(0xFF68686C)
val Gray_2 = Color(0xFFBDBDBF)
val Gray_3 = Color(0xFFD2D2D3)

val Gold = Color(0xFFFFAC33)

val DisabledColor = Color(0xFF9CA6CD)




data class Colors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val card: Color,
    val disabled: Color,
    val primaryShadesLight: Color,
    val secondaryShadesLight: Color,
    val ternaryShadesLight: Color,
    val quaternaryShadesLight: Color,
    val primaryShadesDark: Color,
    val secondaryShadesDark: Color,
    val ternaryShadesDark: Color,
    val quaternaryShadesDark: Color,
)

val LightColors = Colors(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    card = CardLight,
    disabled = DisabledColor,
    primaryShadesLight = PrimaryShadesLight,
    secondaryShadesLight = SecondaryShadesLight,
    ternaryShadesLight = TernaryShadesLight,
    quaternaryShadesLight = QuaternaryShadesLight,
    primaryShadesDark = PrimaryShadesDark,
    secondaryShadesDark = SecondaryShadesDark,
    ternaryShadesDark = TernaryShadesDark,
    quaternaryShadesDark = QuaternaryShadesDark,
)

val DarkColors = Colors(
        primary = PrimaryLight,
        secondary = SecondaryDark,
        background = BackgroundDark,
        card = CardDark,
        disabled = DisabledColor,
        primaryShadesLight = PrimaryShadesLight,
        secondaryShadesLight = SecondaryShadesLight,
        ternaryShadesLight = TernaryShadesLight,
        quaternaryShadesLight = QuaternaryShadesLight,
        primaryShadesDark = PrimaryShadesDark,
        secondaryShadesDark = SecondaryShadesDark,
        ternaryShadesDark = TernaryShadesDark,
        quaternaryShadesDark = QuaternaryShadesDark,
)
