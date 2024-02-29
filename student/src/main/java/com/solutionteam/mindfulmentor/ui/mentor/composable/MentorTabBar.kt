package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
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
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Green
import com.solutionteam.design_system.theme.Red
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.mentor.MeetingUIState
import com.solutionteam.mindfulmentor.ui.mentor.SummeryDetailsUIState
import com.solutionteam.mindfulmentor.utils.formatTimestamp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SummeryScreen(
    summeryList: List<SummeryDetailsUIState> = emptyList(),
    isVideo: Boolean = false,
    onBuy: () -> Unit = {},
    onNavigate: () -> Unit = {}
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Theme.colors.background),
        maxItemsInEachRow = 2,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.Start,
    ) {

        summeryList.forEach {
            SummeryItems(
                chapterNumber = it.chapterNumber,
                chapterDescription = it.chapterDescription,
                piedPrice = it.piedPrice,
                isVideo = isVideo,
                onBuy = onBuy,
                isBuy = it.isBuy,
                onNavigate = onNavigate
            )
        }
    }
}

@Composable
fun SummeryItems(
    chapterNumber: String,
    chapterDescription: String,
    piedPrice: String,
    isVideo: Boolean,
    isBuy: Boolean,
    onBuy: () -> Unit,
    onNavigate: () -> Unit
) {

    Box(modifier = Modifier
        .padding(horizontal = 10.dp)
        .noRippleEffect { onNavigate() }) {
        Column(
            modifier = Modifier
                .background(
                    color = Theme.colors.secondary,
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth(.47f)
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = if (isVideo) R.drawable.ic_video else R.drawable.ic_document),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.9f)
                    .padding(vertical = 8.dp, horizontal = 4.dp)
            )

            Text(
                text = chapterNumber,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.primaryShadesDark,
            )

            Text(
                text = chapterDescription,
                style = Theme.typography.labelLarge,
                color = Theme.colors.secondaryShadesDark,
            )

            if (!isBuy) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Theme.colors.card,
                                shape = CircleShape
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .noRippleEffect { onBuy() }
                    ) {
                        if (piedPrice.isNotEmpty()) {
                            Text(
                                text = stringResource(id = R.string.download_title),
                                style = Theme.typography.labelMedium,
                                color = Theme.colors.primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cart),
                                contentDescription = null,
                                tint = Theme.colors.primary
                            )
                        }
                    }

                    Text(
                        text = piedPrice.ifEmpty { stringResource(id = R.string.free) },
                        style = Theme.typography.bodyLarge,
                        color = if (piedPrice.isEmpty()) Green else Red
                    )
                }
            }
        }
    }
}



@Composable
fun MeetingScreen(
    meetingList: List<MeetingUIState>,
    onClickMeeting: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Theme.colors.background),
    ) {
        meetingList.forEach { meeting ->
            MeetingItem(
                meeting = meeting,
                onClickMeeting
            )
        }
    }
}

@Composable
fun MeetingItem(
    meeting: MeetingUIState,
    onClickMeeting: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = Theme.colors.card,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = meeting.subject,
                style = Theme.typography.titleSmall,
                color = Theme.colors.primaryShadesDark,
            )

            if (meeting.price != "") {
                Text(
                    text = meeting.price + "$",
                    style = Theme.typography.titleSmall,
                    color = Theme.colors.primaryShadesDark,
                )
            }
        }

        Text(
            text = meeting.time.formatTimestamp(),
            style = Theme.typography.labelLarge,
            color = Theme.colors.primaryShadesDark,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = meeting.notes,
            style = Theme.typography.labelLarge,
            color = Theme.colors.secondaryShadesDark,
            modifier = Modifier.padding(8.dp)
        )

        if (!meeting.isBook) {
            GGButton(
                title = "Book now", onClick = onClickMeeting, modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

