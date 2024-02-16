package com.solutionteam.mindfulmentor.ui.notification

import com.solutionteam.mindfulmentor.ui.home.HomeUIEffect
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType

sealed interface NotificationUIEffect {
    data object NotificationError : NotificationUIEffect

    data class NavigateToScreens(val type: NotificationType) : NotificationUIEffect

}
