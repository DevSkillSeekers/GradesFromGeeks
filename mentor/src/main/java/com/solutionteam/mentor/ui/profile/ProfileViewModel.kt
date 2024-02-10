package com.solutionteam.mentor.ui.profile

import androidx.lifecycle.viewModelScope
import com.solutionteam.mentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()) {

    init {
        getData()
        getLanguage()
        getTheme()
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
    private fun getTheme() {
        viewModelScope.launch {
            mindfulMentorRepository.getTheme().distinctUntilChanged().collectLatest { isDark ->
                updateState { it.copy(isDarkTheme = isDark) }
            }
        }
    }

    fun onDismissThemeRequest() {
        updateState { it.copy(showBottomSheetTheme = false) }
    }

    fun onThemeClicked() {
        updateState { it.copy(showBottomSheetTheme = true, showBottomSheetLanguage = false) }
    }

    fun onThemeChanged(isDark: Boolean) {
        mindfulMentorRepository.setTheme(isDark)
        updateState { it.copy(isDarkTheme = isDark) }
    }
    //endregion

    //region Language
    private fun getLanguage() {
        viewModelScope.launch {
            mindfulMentorRepository.getLanguage().distinctUntilChanged().collectLatest { lang ->
                updateState { it.copy(currentLanguage = lang) }
            }
        }
    }

    fun onLanguageClicked() {
        updateState { it.copy(showBottomSheetTheme = false, showBottomSheetLanguage = true) }
    }

    fun onDismissLanguageRequest() {
        updateState { it.copy(showBottomSheetLanguage = false) }
    }

    fun onLanguageChanged(selectedLanguage: Language) {
        mindfulMentorRepository.saveLanguage(selectedLanguage)
        updateState { it.copy(currentLanguage = selectedLanguage, showBottomSheetLanguage = false) }
    }
    //endregion
}
