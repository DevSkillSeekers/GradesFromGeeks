package com.solutionteam.mindfulmentor.ui.presentation.chat.composable

import android.os.Build
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.presentation.chat.MessageUIState

@Composable
fun MessageCard(
    message: MessageUIState,
    modifier: Modifier = Modifier,
) {

    Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(modifier),
            contentAlignment = if (message.isMe) Alignment.CenterEnd else Alignment.CenterStart,
    ) {

        Text(
                modifier = Modifier
                    .background(
                            color = if (message.isMe) {
                                Theme.colors.primary
                            } else {
                                Theme.colors.primaryShadesLight
                            },
                            shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    topEnd = 8.dp,
                                    bottomStart = if (message.isMe) 8.dp else 0.dp,
                                    bottomEnd = if (!message.isMe) 8.dp else 0.dp
                            )
                    )
                    .padding(16.dp)
                ,
                text = message.message.parseHtml(),
                style = Theme.typography.bodyMedium,
                textAlign = if (message.isMe) {
                    TextAlign.End
                } else {
                    TextAlign.Start
                },
                color = if (message.isMe) {
                    Theme.colors.primaryShadesLight
                } else {
                    Theme.colors.primaryShadesDark
                },
        )
    }
}
fun String.parseHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}
