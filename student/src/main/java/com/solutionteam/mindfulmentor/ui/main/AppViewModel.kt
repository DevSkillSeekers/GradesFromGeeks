package com.solutionteam.mindfulmentor.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutionteam.mindfulmentor.data.network.repositories.MindfulMentorRepository
import com.solutionteam.mindfulmentor.ui.profile.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class AppViewModel(private val repository: MindfulMentorRepository) : ViewModel(), KoinComponent {

    private val _language: MutableStateFlow<Language> = MutableStateFlow(Language.ENGLISH)
    val language: StateFlow<Language> = _language.asStateFlow()

    init {
        getLanguage()
    }
    private fun getLanguage() {
        viewModelScope.launch {
            repository.getLanguage().distinctUntilChanged().collectLatest { lang ->
                _language.update { lang }
            }
        }
    }

}
