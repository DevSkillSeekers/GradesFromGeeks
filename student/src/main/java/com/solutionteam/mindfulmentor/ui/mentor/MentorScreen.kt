package com.solutionteam.mindfulmentor.ui.mentor

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.mentor.composable.ImageWithShadowComponent
import com.solutionteam.mindfulmentor.ui.mentor.composable.MentorProfileDetails
import com.solutionteam.mindfulmentor.ui.mentor.composable.MentorSummeryNumbers
import com.solutionteam.mindfulmentor.ui.mentor.composable.MentorTabBar
import com.solutionteam.mindfulmentor.ui.mentor.composable.SubjectComposable
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun MentorScreen(
    viewModel: MentorViewModel = koinViewModel(),
    onNavigateTo: () -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()


    MentorContent(
        state = state,
        onBack = navigateBack
    )

    val color = Theme.colors.primary
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


private fun onEffect(effect: MentorUIEffect?, context: Context) {

    when (effect) {
        MentorUIEffect.MentorError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun MentorContent(
    state: MentorUIState,
    onBack: () -> Unit
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
                            .height(200.dp)
                            .constrainAs(imageWithShadow) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        onBack = onBack
                    )

                    MentorProfileDetails(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(profileDetails) {
                                top.linkTo(imageWithShadow.top, margin = 50.dp)
                                start.linkTo(imageWithShadow.start, margin = 50.dp)
                                end.linkTo(imageWithShadow.end)
                                bottom.linkTo(imageWithShadow.bottom, margin = 24.dp)
                            }
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

                        MentorSummeryNumbers()
                        SubjectComposable()
                        MentorTabBar()
                    }

                }
            }
        }
    }
}
