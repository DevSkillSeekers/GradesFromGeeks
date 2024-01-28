package com.solutionteam.mindfulmentor.ui.presentation.auth.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.utils.Utils

@OptIn(ExperimentalTextApi::class)
@Composable
fun TextWithClick(
    fullText: String,
    linkText: String,
    url: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.colors.primary,
    style: TextStyle = Theme.typography.labelLarge
) {
    val annotatedLinkString = Utils.textWithLink(fullText, linkText, url, color)
    val uriHandler = LocalUriHandler.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ClickableText(
            modifier = modifier,
            style = style.copy(textAlign = TextAlign.Center).copy(color = Theme.colors.secondaryShadesDark),
            text = annotatedLinkString,
            onClick = {
                annotatedLinkString
                    .getUrlAnnotations(it, it)
                    .firstOrNull()?.let { annotation ->
                        uriHandler.openUri(annotation.item.url)
                    }
            }
        )
    }
}
@Composable
fun TextWithClick(
    onClick:()->Unit,
    fullText: String,
    linkText: String,
    modifier: Modifier = Modifier,
    url: String="",
    color: Color = Theme.colors.primary,
    style: TextStyle = Theme.typography.labelLarge,
) {
    val annotatedLinkString = Utils.textWithLink(fullText, linkText, url, color)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ClickableText(
            modifier = modifier,
            style = style.copy(textAlign = TextAlign.Center),
            text = annotatedLinkString,
            onClick = {
                onClick()
            }
        )
    }
}
