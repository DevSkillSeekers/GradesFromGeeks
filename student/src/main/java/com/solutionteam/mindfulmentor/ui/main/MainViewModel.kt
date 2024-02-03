package com.solutionteam.mindfulmentor.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val mindfulMentorRepository: MindfulMentorRepository
) : ViewModel() {

    private val _isFirstTimeOpenApp: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isFirstTimeOpenApp: StateFlow<Boolean?> = _isFirstTimeOpenApp.asStateFlow()

    init {
        getInitScreen()
        saveIsFirstTimeOpenApp()
    }
    private fun saveIsFirstTimeOpenApp() {
        viewModelScope.launch(Dispatchers.IO) {
            mindfulMentorRepository.saveIsFirstTimeUseApp(false)
        }
    }

    private fun getInitScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            _isFirstTimeOpenApp.update { mindfulMentorRepository.getIsFirstTimeUseApp() }
        }
    }
}