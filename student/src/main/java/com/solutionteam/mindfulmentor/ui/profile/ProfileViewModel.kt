package com.solutionteam.mindfulmentor.ui.profile

import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.base.BaseViewModel
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
            mindfulMentorRepository.getTheme().distinctUntilChanged().collectLatest { theme ->
                updateState { it.copy(isDarkTheme = theme ?: false) }
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
        viewModelScope.launch {
            mindfulMentorRepository.setTheme(isDark)
            updateState { it.copy(isDarkTheme = isDark) }
        }
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
        viewModelScope.launch {
            mindfulMentorRepository.saveLanguage(selectedLanguage)
            updateState {
                it.copy(
                    currentLanguage = selectedLanguage,
                    showBottomSheetLanguage = false
                )
            }
        }
    }
    //endregion
}
