package com.solutionteam.mindfulmentor.ui.individualMeeting.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGTextChipStyle
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.individualMeeting.AvailableDateUiState
import com.solutionteam.mindfulmentor.ui.individualMeeting.TimeUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailableTimePerDay(
    day: AvailableDateUiState,
    modifier: Modifier = Modifier,
    onTimeSelected: (TimeUiState,String) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = day.day,
            textAlign = TextAlign.Start,
            style = Theme.typography.bodyLarge
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            day.times.forEach { time ->
                GGTextChipStyle(
                    modifier = Modifier
                        .noRippleEffect { onTimeSelected(time,day.day) },
                    value = time.time,
                    backgroundColor = if (time.isSelected) {
                        Theme.colors.primary
                    } else {
                        Theme.colors.card
                    },
                    textColor =  if (time.isSelected) {
                        Theme.colors.secondary
                    } else {
                        Theme.colors.primaryShadesDark
                    },
                    paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
