package com.solutionteam.mindfulmentor.ui.mentor.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Gray_1
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState

@Composable
fun ContentCountCard(
    contentCountList: List<ContentCountUIState>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(
                    color = Theme.colors.card,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            contentCountList.forEachIndexed { index, contentCountUIState ->
                MentorItems(
                    number = contentCountUIState.count,
                    description = contentCountUIState.contentName
                )

                if (index != contentCountList.size - 1) {
                    Divider(
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.dp),
                        color = Gray_1
                    )
                }
            }
        }
    }
}

@Composable
private fun MentorItems(
    number: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = number,
            style = Theme.typography.labelMedium,
            color = Theme.colors.primaryShadesDark
        )

        Text(
            text = description,
            style = Theme.typography.labelLarge,
            color = Theme.colors.ternaryShadesDark
        )
    }
}