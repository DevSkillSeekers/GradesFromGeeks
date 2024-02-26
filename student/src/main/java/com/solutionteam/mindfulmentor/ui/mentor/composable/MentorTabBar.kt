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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Green
import com.solutionteam.design_system.theme.Red
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.mentor.SummeryDetailsUIState


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SummeryScreen(
    summeryList: List<SummeryDetailsUIState> = emptyList(),
    isVideo: Boolean = false,
    onClick: () -> Unit = {}
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Theme.colors.background),
        maxItemsInEachRow = 2,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        summeryList.forEach {
            SummeryItems(
                chapterNumber = it.chapterNumber,
                chapterDescription = it.chapterDescription,
                piedPrice = it.piedPrice,
                isVideo = isVideo,
                onClick = onClick
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
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = Theme.colors.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = if (isVideo) R.drawable.ic_video else R.drawable.ic_document),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(.4f)
                .wrapContentHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp)
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

        Row(
            modifier = Modifier
                .fillMaxWidth(.45f)
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
                    .noRippleEffect { onClick() }
            ) {
                if (piedPrice.isNotEmpty()) {
                    Text(
                        text = "Download",
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
                text = piedPrice.ifEmpty { "Free" },
                style = Theme.typography.bodyLarge,
                color = if (piedPrice.isEmpty()) Green else Red
            )
        }
    }
}


val fakeSummeryList = listOf(
    SummeryDetailsUIState(
        chapterNumber = "Chapter 1",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 2",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 3",
        chapterDescription = "15 page (pdf)",
        piedPrice = "10$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 4",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 5",
        chapterDescription = "15 page (pdf)",
        piedPrice = "5$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 6",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 3",
        chapterDescription = "15 page (pdf)",
        piedPrice = "10$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 4",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 5",
        chapterDescription = "15 page (pdf)",
        piedPrice = "5$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 6",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 3",
        chapterDescription = "15 page (pdf)",
        piedPrice = "10$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 4",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 5",
        chapterDescription = "15 page (pdf)",
        piedPrice = "5$"
    ),
    SummeryDetailsUIState(
        chapterNumber = "Chapter 6",
        chapterDescription = "15 page (pdf)",
        piedPrice = ""
    ),
)

@Composable
fun MeetingScreen(
    onClickMeeting: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Theme.colors.background),
    ) {
        fakeSummeryList.forEach { _ ->
            MeetingItem(onClickMeeting)
        }
    }
}

@Composable
fun MeetingItem(
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
                text = "OOP Cracking",
                style = Theme.typography.titleSmall,
                color = Theme.colors.primaryShadesDark,
            )

            Text(
                text = "30$",
                style = Theme.typography.titleSmall,
                color = Theme.colors.primaryShadesDark,
            )
        }

        Text(
            text = "Thr 25 May in 10 :23 PM",
            style = Theme.typography.labelLarge,
            color = Theme.colors.primaryShadesDark,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "This meet to recap oop structure from ch2 to ch 5",
            style = Theme.typography.labelLarge,
            color = Theme.colors.secondaryShadesDark,
            modifier = Modifier.padding(8.dp)
        )

        GGButton(
            title = "Book now", onClick = onClickMeeting, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

