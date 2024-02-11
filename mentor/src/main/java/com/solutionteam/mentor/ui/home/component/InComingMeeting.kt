package com.solutionteam.mentor.ui.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mentor.R
import com.solutionteam.mentor.data.entity.MeetingType
import com.solutionteam.mentor.ui.home.MeetingUiState
import com.solutionteam.mentor.utils.formatHoursMinutes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InComingMeeting(
    meeting: MeetingUiState,
    modifier: Modifier = Modifier,
    onJoinClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .background(Theme.colors.card, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painter = painterResource(
                    id = if (meeting.type == MeetingType.INDIVIDUAL) {
                        R.drawable.ic_profile_unselected
                    } else {
                        R.drawable.group
                    }
                ),
                contentDescription = null,
                tint = Theme.colors.primary
            )

            Text(
                text = meeting.subject,
                style = Theme.typography.titleSmall,
                color = Theme.colors.primaryShadesDark,
                maxLines = 1,
                modifier = Modifier.basicMarquee()
            )
        }

        Text(
            text = meeting.notes,
            maxLines = 2,
            style = Theme.typography.labelLarge
        )

        GGButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = if (meeting.enableJoin) {
                stringResource(id = R.string.join_now)
            } else {
                stringResource(R.string.join_after) + "(${meeting.reminder.formatHoursMinutes()})"
            },
            enabled = meeting.enableJoin,
            onClick = onJoinClicked
        )
    }
}
