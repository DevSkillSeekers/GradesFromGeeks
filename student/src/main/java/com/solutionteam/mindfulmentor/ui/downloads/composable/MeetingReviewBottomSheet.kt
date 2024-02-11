package com.solutionteam.mindfulmentor.ui.downloads.composable

import RatingStar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.components.GGBottomSheet
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGTextField
import com.solutionteam.design_system.theme.PlusJakartaSans
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R

@Composable
fun MeetingReviewBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {

    GGBottomSheet(
        modifier = modifier.padding(top = 0.dp),
        onDismissRequest = onDismissRequest,
        showDragHandle = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3.5f)
                .background(color = Theme.colors.primaryShadesDark),
            contentAlignment = Alignment.Center
        ) {

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "play",
                    tint = Theme.colors.background
                )
            }
        }

        GGMentor(
            modifier = Modifier.padding(16.dp),
            name = "Amnah Ali",
            isForSubject = true,
            subjectName = "Data Structure - lecture 1",
            profileUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
            onClick = {}
        )


        var rating by remember { mutableFloatStateOf(4.5f) }

        RatingStar(
            rating = rating,
            maxRating = 5,
            onStarClick = {
                rating = it.toFloat()
            }, false
        )


        GGTextField(
            text = "",
            onValueChange = {},
            hint = "Write Your Review",
            textStyle = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(16.dp),
            focusedBorderColor = Color.Transparent,
            hintTextAlign = TextAlign.Center,
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            GGButton(
                title = "Submit",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .padding(bottom = 24.dp)
            )
        }
    }
}