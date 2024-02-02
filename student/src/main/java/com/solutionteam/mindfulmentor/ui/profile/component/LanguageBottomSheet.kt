package com.solutionteam.mindfulmentor.ui.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGBottomSheet
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.design_system.theme.mindfulMentorTypography
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.profile.Language

@Composable
fun LanguageBottomSheet(
    selectedLanguage: Language,
    languages: List<Language>,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onLanguageChanged: (Language) -> Unit
) {
    GGBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = R.string.app_language),
            style = mindfulMentorTypography.titleSmall,
            color = Theme.colors.primaryShadesDark
        )

        SegmentOfLanguage(
            modifier = Modifier.padding(vertical = 16.dp),
            items = languages,
            language = selectedLanguage,
            onItemSelection = onLanguageChanged
        )
    }
}

@Composable
private fun SegmentOfLanguage(
    language: Language,
    items: List<Language>,
    modifier: Modifier = Modifier,
    onItemSelection: (Language) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .wrapContentSize()
            .background(Theme.colors.card, shape = RoundedCornerShape(100.dp))
    ) {
        items.forEach { item ->
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(
                        color = if (item.abbreviation == language.abbreviation) Theme.colors.primary else Theme.colors.card
                    )
                    .noRippleEffect {
                        onItemSelection(item)
                    }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = item.value,
                    color = if (item.abbreviation == language.abbreviation) Theme.colors.secondary else Theme.colors.primaryShadesDark,
                    style = mindfulMentorTypography.bodyLarge
                )
            }
        }
    }
}
