package com.solutionteam.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme

@Composable
fun GGTabBar(
    tabs: List<Pair<String, @Composable () -> Unit>>,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Theme.colors.background)
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            divider = {
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                        .padding(horizontal = 16.dp)
                )
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .background(
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                            color = Theme.colors.primary
                        ),
                    height = 4.dp,
                    color = Theme.colors.primary
                )
            },
            containerColor = Theme.colors.background,
            contentColor = Theme.colors.primaryShadesDark,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Theme.colors.background)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)

        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        Text(
                            tab.first,
                            style = Theme.typography.bodyMedium,
                            color = if (tabIndex == index) Theme.colors.primary else Theme.colors.secondaryShadesDark
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    interactionSource = MutableInteractionSource()
                )
            }
        }

        tabs.getOrNull(tabIndex)?.second?.invoke()
    }
}