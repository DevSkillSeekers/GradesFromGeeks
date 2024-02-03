package com.solutionteam.mindfulmentor.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.profile.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class AppViewModel(
    private val repository: MindfulMentorRepository
) : ViewModel(), KoinComponent {

    private val _language: MutableStateFlow<Language> = MutableStateFlow(Language.ENGLISH)
    val language: StateFlow<Language> = _language.asStateFlow()

    private val _theme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val theme: StateFlow<Boolean> = _theme.asStateFlow()

    private val _isFirstTimeOpenApp: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isFirstTimeOpenApp: StateFlow<Boolean?> = _isFirstTimeOpenApp.asStateFlow()


    init {
        getLanguage()
        getTheme()
        getInitScreen()
        saveIsFirstTimeOpenApp()
    }

    private fun saveIsFirstTimeOpenApp() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveIsFirstTimeUseApp(false)
        }
    }

    private fun getInitScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            _isFirstTimeOpenApp.update { repository.getIsFirstTimeUseApp() }
        }
    }
    private fun getLanguage() {
        viewModelScope.launch {
            repository.getLanguage().distinctUntilChanged().collectLatest { lang ->
                _language.update { lang }
            }
        }
    }

    private fun getTheme() {
        viewModelScope.launch {
            repository.getTheme().distinctUntilChanged().collectLatest { isDark ->
                _theme.update { isDark }
            }
        }
    }

}
