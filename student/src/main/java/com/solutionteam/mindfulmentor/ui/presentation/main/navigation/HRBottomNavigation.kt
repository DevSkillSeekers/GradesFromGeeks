package com.solutionteam.mindfulmentor.ui.presentation.main.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun HRBottomNavigation(
    screens: List<Screen>,
    onNavigateTo: (Screen) -> Unit,
    currentDestination: NavDestination?
) {

    Log.d("navigation", "HomeBottomNavigation")

    AnimatedVisibility(
        visible = true,
    ) {


        AppBottomBar {

            screens.forEach { screen ->
                Log.d("navigation", "HomeBottomNavigation: hierarchy = $currentDestination")

                val selected: Boolean =
                    currentDestination?.hierarchy?.any { it.route == screen.route } ?: false

                AppBottomBarItem(
                    selected = selected,
                    onClick = { onNavigateTo(screen) },
                    icon = {
                        Icon(
                            imageVector = screen.icon ?: Icons.Default.Warning,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(id = screen.title)) }
                )
            }
        }
    }
}

@Composable
private fun AppBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
private fun RowScope.AppBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground,
            indicatorColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}