package com.solutionteam.mindfulmentor.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.PlusJakartaSans
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@Composable
fun NotificationItem(
    notification: NotificationState,
    onNotificationClick: (NotificationState) -> Unit
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Theme.colors.card)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .noRippleEffect { onNotificationClick(notification) },
            verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(modifier= Modifier.align(Alignment.CenterVertically)
            .background(Theme.colors.secondary, shape = CircleShape)) {
            Image(
                    painter = painterResource(id = notification.subjectIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    contentScale = ContentScale.Crop
            )
        }


        Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
        ) {

            val notificationType = when (notification.type) {
                NotificationType.MEETING_ACCEPTED  -> stringResource(R.string.accepted_your_request_for_one_one_meeting)
                NotificationType.UPCOMING_MEETING-> stringResource(R.string.meeting_with_you_will_come_soon_stay_tuned)
                NotificationType.NEW_SCHEDULE_MEETING_GROUP-> stringResource(R.string.has_scheduled_an_upcoming_meeting)
                NotificationType.NEW_VIDEO -> stringResource(R.string.published_a_new_video)
                NotificationType.NEW_SUMMARY-> stringResource(R.string.published_a_new_summary)
            }

            Text(
                    buildAnnotatedString {
                        withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = PlusJakartaSans,fontSize = 12.sp)
                        ) {
                            append(notification.mentorName)
                        }
                        withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Normal, fontFamily = PlusJakartaSans,fontSize = 12.sp)
                        ) {
                            append(" $notificationType")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 20.sp,
                    color = Theme.colors.primaryShadesDark,
            )
            Text(
                    text = notification.timeCreated,
                    color = Theme.colors.ternaryShadesDark,
                    style = Theme.typography.labelLarge,
                    modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (!notification.viewed) {
            Box(modifier = Modifier) {
                Image(
                        modifier = Modifier
                            .size(16.dp)
                            .fillMaxHeight(),
                        colorFilter = ColorFilter.tint(Theme.colors.primary),
                        painter = painterResource(R.drawable.ic_circle),
                        contentDescription = "notification icon",
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFE6E6E6)
@Composable
fun NotificationItemPreview() {
    NotificationItem(
            notification = NotificationState(
                    id = 1,
                    mentorId = 0,
                    mentorName = "John Doe",
                    timeCreated = "1 hour ago",
                    type = NotificationType.NEW_VIDEO,
                    viewed = false,
            ),
            onNotificationClick = {}
    )
}
