package com.solutionteam.mindfulmentor.ui.subject

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGTitleWithSeeAll
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.data.entity.Mentor
import com.solutionteam.mindfulmentor.ui.mentor.composable.ContentCountCard
import com.solutionteam.mindfulmentor.ui.mentor.composable.ImageWithShadowComponent
import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SubjectScreen(
    viewModel: SubjectViewModel = koinViewModel(),
    onNavigateTo: (SubjectUIEffect) -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()


    UniversityContent(
        state = state,
        onBack = navigateBack,
        navigateToSeeAll = { onNavigateTo(SubjectUIEffect.NavigateToSeeAll) },
        navigateToMentorProfile = { onNavigateTo(SubjectUIEffect.NavigateToMentorProfile) }
    )

    val color = Theme.colors.primary
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            statusBarColor = color,
        )
    }

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: SubjectUIEffect?, context: Context) {

    when (effect) {
        SubjectUIEffect.SubjectError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun UniversityContent(
    state: SubjectUIState,
    onBack: () -> Unit,
    navigateToSeeAll: () -> Unit,
    navigateToMentorProfile: () -> Unit
) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Theme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                ) {
                    val (imageWithShadow, profileDetails, profileCorner) = createRefs()

                    ImageWithShadowComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(124.dp)
                            .constrainAs(imageWithShadow) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        imageUrl = "",
                        shadowAlpha = 1f,
                        onBack = onBack
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .constrainAs(profileDetails) {
                                top.linkTo(parent.top, margin = 8.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "Data Structure",
                            style = Theme.typography.titleMedium,
                            color = Theme.colors.background,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(
                                Theme.colors.background,
                                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                            )
                            .padding(top = 24.dp)
                            .constrainAs(profileCorner) {
                                top.linkTo(imageWithShadow.bottom, margin = (-24).dp)
                                start.linkTo(imageWithShadow.start)
                                end.linkTo(imageWithShadow.end)
                            },
                    ) {

                        ContentCountCard(
                            contentCountList = listOf(
                                ContentCountUIState("10", stringResource(id = R.string.mentors)),
                                ContentCountUIState("20", stringResource(id = R.string.summaries)),
                                ContentCountUIState("30", stringResource(id = R.string.videos))
                            ),
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        GGTitleWithSeeAll(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            title = stringResource(id = R.string.mentors),
                            showSeeAll = true,
                            onClick = navigateToSeeAll
                        )

                        mentor.forEach {
                            GGMentor(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .padding(horizontal = 8.dp),
                                name = it.name,
                                rate = it.rate,
                                numberReviewers = it.numberReviewers,
                                profileUrl = it.imageUrl,
                                onClick = navigateToMentorProfile
                            )

                        }
                    }

                }
            }
        }
    }
}


private val mentor = listOf(
    Mentor(
        id = "1",
        name = "First Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGuH6Vo5XDGGvgriYJwqI9I8efWEOeVQrVTw&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "First University"
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 30,
        videos = 20,
        meeting = 18,
        subjects = emptyList(),
        university = "Second University"
    ),
    Mentor(
        id = "3",
        name = "Third Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "Third University"
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "First University"
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "First University"
    ),
    Mentor(
        id = "3",
        name = "Third Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "First University"
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
        summaries = 20,
        videos = 16,
        meeting = 18,
        subjects = emptyList(),
        university = "First University"
    )
)
