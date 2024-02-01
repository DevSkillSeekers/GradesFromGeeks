package com.solutionteam.mindfulmentor.ui.profile

data class ProfileUIState(

    val profileUrl: String = "",
    val name: String = "",

    val showBottomSheetTheme: Boolean = false,
    val isDarkTheme: Boolean = false,

    val showBottomSheetLanguage: Boolean = false,
    val languages: List<Language> = getLanguage(),
    val currentLanguage: Language = getLanguage().first(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)

data class Language(
    val name: String = "",
    val abbreviation: String = ""
)


private fun getLanguage(): List<Language> {
    return listOf(
        Language(name = "English", abbreviation = "en"),
        Language(name = "عربي", abbreviation = "ar"),
    )
}
