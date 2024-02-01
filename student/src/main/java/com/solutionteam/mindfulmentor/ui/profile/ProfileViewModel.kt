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
        updateState { it.copy(showBottomSheetThem = false) }
    }

    fun onThemeClicked() {
        updateState { it.copy(showBottomSheetThem = true) }
    }

    fun onThemeChanged(isDark: Boolean) {
        updateState { it.copy(isDarkTheme = isDark) }
    }
    //endregion
}
