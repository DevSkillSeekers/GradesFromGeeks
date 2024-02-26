package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGSubject
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.ui.home.SubjectDetailsUiState

@Composable
fun SubjectComposable(
    subjectList: List<SubjectDetailsUiState>,
    modifier: Modifier = Modifier.padding(vertical = 16.dp)
) {
    val selectedSubject = remember { mutableStateOf(subjectList.first()) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.subjects),
            style = Theme.typography.titleSmall,
            color = Theme.colors.primaryShadesDark,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(subjectList) { subject ->
                GGSubject(
                    name = subject.name,
                    isClicked = selectedSubject.value == subject,
                    isWithBorder = true,
                ) {
                    selectedSubject.value = subject
                }
            }
        }
    }

}