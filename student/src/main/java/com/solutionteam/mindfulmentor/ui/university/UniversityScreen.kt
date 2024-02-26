package com.solutionteam.mindfulmentor.ui.university

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
import androidx.compose.ui.text.style.TextOverflow
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
import com.solutionteam.mindfulmentor.ui.mentor.composable.SubjectComposable
import com.solutionteam.mindfulmentor.ui.mentor.composable.subjectList
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UniversityScreen(
    id: String,
    viewModel: UniversityViewModel = koinViewModel(parameters = { parametersOf(id) }),
    onNavigateTo: (UniversityUIEffect) -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()


    UniversityContent(
        state = state,
        onBack = navigateBack,
        navigateToSeeAll = { onNavigateTo(UniversityUIEffect.NavigateToSeeAll) },
        navigateToMentorProfile = { onNavigateTo(UniversityUIEffect.NavigateToMentorProfile(it)) }
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


private fun onEffect(effect: UniversityUIEffect?, context: Context) {

    when (effect) {
        UniversityUIEffect.UniversityError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun UniversityContent(
    state: UniversityUIState,
    onBack: () -> Unit,
    navigateToSeeAll:() -> Unit,
    navigateToMentorProfile: (String) -> Unit
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
                            .height(180.dp)
                            .constrainAs(imageWithShadow) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        imageUrl = state.universityDetails.universityImageUrl,
                        onBack = onBack
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .constrainAs(profileDetails) {
                                top.linkTo(parent.top, margin = (24).dp)
                                start.linkTo(parent.start, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                                bottom.linkTo(profileCorner.top, margin = 8.dp)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = state.universityDetails.universityName,
                            style = Theme.typography.titleMedium,
                            color = Theme.colors.background,
                            modifier = Modifier.padding(16.dp)
                        )

                        Text(
                            text = state.universityDetails.universityDescription,
                            style = Theme.typography.labelLarge,
                            color = Theme.colors.background,
                            modifier = Modifier.padding(horizontal = 24.dp),
                            overflow = TextOverflow.Ellipsis,
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
                                ContentCountUIState(
                                    state.universityDetails.mentorNumber,
                                    stringResource(id = R.string.mentors)
                                ),
                                ContentCountUIState(
                                    state.universityDetails.summaryNumber,
                                    stringResource(id = R.string.summaries)
                                ),
                                ContentCountUIState(
                                    state.universityDetails.videoNumber,
                                    stringResource(id = R.string.videos)
                                )
                            ),
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        SubjectComposable(
                            modifier = Modifier.padding(top= 16.dp),
                            subjectList = state.universityDetails.subjectList
                        )
                        GGTitleWithSeeAll(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .padding(horizontal = 16.dp),
                            title = stringResource(id = R.string.mentors),
                            showSeeAll = true,
                            onClick = navigateToSeeAll
                        )

                        state.universityDetails.mentorList.forEach {
                            GGMentor(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .padding(horizontal = 8.dp),
                                name = it.name,
                                rate = it.rate,
                                numberReviewers = it.numberReviewers,
                                profileUrl = it.imageUrl,
                                onClick = { navigateToMentorProfile(it.id) }
                            )

                        }
                    }

                }
            }
        }
    }
}
