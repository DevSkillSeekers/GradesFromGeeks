package com.solutionteam.mindfulmentor.ui.profile

import androidx.compose.ui.unit.LayoutDirection

data class ProfileUIState(

    val profileUrl: String = "",
    val name: String = "",

    val showBottomSheetTheme: Boolean = false,
    val isDarkTheme: Boolean = false,

    val showBottomSheetLanguage: Boolean = false,
    val languages: List<Language> = Language.entries,
    val currentLanguage: Language = Language.ENGLISH,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)

enum class Language(
    val value: String, val abbreviation: String, val layoutDirection: LayoutDirection
) {
    ENGLISH(value = "English", "en", layoutDirection = LayoutDirection.Ltr),
    ARABIC(value = "عربي", "ar", layoutDirection = LayoutDirection.Rtl)
}

fun String?.getLanguage(): Language {
    Language.entries.forEach {
        if (it.abbreviation == this) {
            return it
        }
    }
    return Language.ENGLISH
}

