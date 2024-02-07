package com.solutionteam.mindfulmentor.ui.review

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
fun ReviewScreen(
    viewModel: ReviewViewModel = koinViewModel(),
    onNavigateTo: (ReviewUIEffect) -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()


    ReviewContent(
        state = state,
        onBack = navigateBack,
        navigateToSeeAll = { onNavigateTo(ReviewUIEffect.NavigateToSeeAll) },
        navigateToMentorProfile = { onNavigateTo(ReviewUIEffect.NavigateToMentorProfile) }
    )

    val color = Theme.colors.primaryShadesDark
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: ReviewUIEffect?, context: Context) {

    when (effect) {
        ReviewUIEffect.ReviewError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun ReviewContent(
    state: ReviewUIState,
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
                    val (imageWithShadow, profileCorner) = createRefs()

                    ImageWithShadowComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                            .background(Theme.colors.primaryShadesDark)
                            .constrainAs(imageWithShadow) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        imageUrl = "",
                        shadowAlpha = 0f,
                        onBack = onBack
                    )


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
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    ),
    Mentor(
        id = "3",
        name = "Third Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    ),
    Mentor(
        id = "3",
        name = "Third Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo5xoN3QF2DBxrVUq7FSxymtDoD3-_IW5CgQ&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    ),
    Mentor(
        id = "2",
        name = "Second Mentor",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
        rate = 4.5,
        numberReviewers = 500,
    )
)
