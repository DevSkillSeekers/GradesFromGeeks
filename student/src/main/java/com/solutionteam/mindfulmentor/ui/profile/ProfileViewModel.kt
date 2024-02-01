package com.solutionteam.mindfulmentor.ui.profile

import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel

class ProfileViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()) {

    init {
        getData()
    }

    private fun getData() {
        updateState {
            it.copy(
                profileUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
                name = "Asia Sama"
            )
        }
    }


    //region Theme
    fun onDismissThemeRequest() {
        updateState { it.copy(showBottomSheetTheme = false) }
    }

    fun onThemeClicked() {
        updateState { it.copy(showBottomSheetTheme = true, showBottomSheetLanguage = false) }
    }

    fun onThemeChanged(isDark: Boolean) {
        updateState { it.copy(isDarkTheme = isDark) }
    }
    //endregion

    //region Language
    fun onLanguageClicked() {
        updateState { it.copy(showBottomSheetTheme = false, showBottomSheetLanguage = true) }
    }

    fun onDismissLanguageRequest() {
        updateState { it.copy(showBottomSheetLanguage = false) }
    }

    fun onLanguageChanged(selectedLanguage: Language) {
        updateState { it.copy(currentLanguage = selectedLanguage) }
    }
    //endregion
}
