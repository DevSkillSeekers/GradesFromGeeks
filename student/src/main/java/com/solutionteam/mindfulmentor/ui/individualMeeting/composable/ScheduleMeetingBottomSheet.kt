package com.solutionteam.mindfulmentor.ui.individualMeeting.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGBottomSheet
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGTextField
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.design_system.theme.mindfulMentorTypography
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.individualMeeting.TimeUiState

@Composable
fun ScheduleMeetingBottomSheet(
    timeUiState: TimeUiState,
    note: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    onBookClick: () -> Unit
) {

    GGBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 8.dp),
            text = timeUiState.day,
            style = mindfulMentorTypography.titleSmall,
            color = Theme.colors.primaryShadesDark
        )

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = timeUiState.time,
            style = mindfulMentorTypography.bodyMedium,
            color = Theme.colors.primaryShadesDark
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 14.dp, top = 8.dp),
            text = "Note",
            style = mindfulMentorTypography.bodyLarge,
            color = Theme.colors.primaryShadesDark
        )

        GGTextField(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            hint = stringResource(id = R.string.why_meeting),
            text = note,
            onValueChange = onValueChange,
            shapeRadius = RoundedCornerShape(16.dp)
        )

        GGButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 16.dp),
            title = stringResource(id = R.string.book),
            enabled = note.isNotEmpty(),
            onClick = onBookClick
        )

    }

}
