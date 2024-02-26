package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGSubject
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.data.entity.Subject

@Composable
fun SubjectComposable(
    subjectList: List<Subject>,
    modifier: Modifier = Modifier.padding(vertical = 16.dp)
) {
    val selectedSubject = remember { mutableStateOf(subjectList.first()) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Subject",
            style = Theme.typography.titleSmall,
            color = Theme.colors.primaryShadesDark,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            state = rememberLazyListState(),
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

val subjectList = listOf(
    Subject(id = "1", "OOP"),
    Subject(id = "2", "Design System"),
    Subject(id = "3",  "Kotlin"),
    Subject(id = "4", "Java"),
    Subject(id = "5", "C++"),
)