package com.solutionteam.mindfulmentor.ui.theme

import androidx.compose.ui.graphics.Color

val MainColor = Color(0xFF0A172C)
val SecondColor = Color(0xFFA3B0C4)
val BackgroundColor = Color(0xFFF9F9F9)


val Gray_66 = Color(0xFF666666)
val Gray_8C = Color(0xFF8C8C8C)
val Gray_CC = Color(0xFFCCCCCC)
val NormalFontColor = Color(0xFF1C1C1C)
val MainFontColor = Color(0xFF111111)
val Hamesh = Color(0xFFAFAFAF)
val Yalow = Color(0xFFFFCC16)
val DisabledColor = Color(0xFF656565)
val Pink = Color(0xFFC27C7C)

val Black_100 = Color(0xFF000000)
val Black_87 = Color(0xDE121212)
val Black_60 = Color(0x99121212)
val Black_37 = Color(0x5E121212)
val Black_16 = Color(0x29121212)
val Black_30 = Color(0x4D121212)
val Black_10 = Color(0x1A121212)

val White_FF = Color(0xFFFFFFFF)
val White_FA = Color(0xFFFAFAFA)
val White_EC = Color(0xFFECECEC)
val White_E1 = Color(0xFFE1E1E1)
val White_E3 = Color(0xFFE3E3E3)

data class Colors(
    val mainColor: Color,
    val secondColor: Color,
    val backgroundColor: Color,
    val normalFontColor: Color,
    val mainFontColor: Color,
    val hamesh: Color,
    val star: Color,
    val disabledColor: Color,
    val notificationColor: Color
)

val LightColors = Colors(
        mainColor = MainColor,
        secondColor = SecondColor,
        backgroundColor = BackgroundColor,
        normalFontColor = NormalFontColor,
        mainFontColor = MainFontColor,
        hamesh = Hamesh,
        star = Yalow,
        disabledColor = DisabledColor,
        notificationColor = Pink,
)

val DarkColors = Colors(
        mainColor = MainColor,
        secondColor = SecondColor,
        backgroundColor = MainColor,
        normalFontColor = NormalFontColor,
        mainFontColor = MainFontColor,
        hamesh = Hamesh,
        star = Yalow,
        disabledColor = DisabledColor,
        notificationColor = Pink,
)