package com.solutionteam.mentor.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

object Utils {
    @OptIn(ExperimentalTextApi::class)
    fun textWithLink(fullText: String, linkText: String, url: String, color:Color): AnnotatedString {
        val annotatedLinkString: AnnotatedString = buildAnnotatedString {
            val startIndex = fullText.indexOf(linkText)
            val endIndex = startIndex + linkText.length
            append(fullText)
            addStyle(
                style = SpanStyle(
                    color = color,
                    textDecoration = TextDecoration.None
                ), start = startIndex, end = endIndex
            )

            addUrlAnnotation(
                UrlAnnotation(url),
                start = startIndex,
                end = endIndex
            )
        }
        return annotatedLinkString
    }
}
