package com.solutionteam.mindfulmentor.ui.review

import RatingStar
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGPreferencesCard
import com.solutionteam.design_system.components.GGTabBar
import com.solutionteam.design_system.components.GGTextField
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.PlusJakartaSans
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReviewScreen(
    viewModel: ReviewViewModel = koinViewModel(),
    onNavigateTo: () -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    ReviewContent(
        state = state,
        onBack = navigateBack,
        onNavigateToVideoScreen = onNavigateTo
    )

    val color = Theme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            navigationBarColor = color,
            statusBarColor = color,
            isDarkIcon = true
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
    onNavigateToVideoScreen: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            GGAppBar(title = "Data Structure-lecture 1", onBack = onBack)
        }
    ) { padding ->
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.3f)
                        .background(Theme.colors.primaryShadesDark),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(
                        onClick = onNavigateToVideoScreen
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fill_screen),
                            contentDescription = null,
                            tint = Theme.colors.card
                        )
                    }
                }

                GGTabBar(
                    tabs = listOf(
                        "Info" to { InfoComposable() },
                        "Review" to { ReviewComposable() },
                        "Summarize" to { SummarizeComposable() }
                    ),
                )
            }
        }
    }
}


@Composable
private fun InfoComposable() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        GGMentor(
            modifier = Modifier.padding(16.dp),
            name = "Amnah Ali",
            isForSubject = true,
            subjectName = "Data Structure - lecture 1",
            profileUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_p4wGt_hng5BeADmgd6lf0wPrY6aOssc3RA&usqp=CAU",
            onClick = {}
        )

        GGPreferencesCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.help),
            painter = painterResource(id = R.drawable.ic_help),
            onClick = {}
        )
    }
}


@Composable
private fun ReviewComposable() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        var rating by remember { mutableFloatStateOf(4.5f) }

        RatingStar(
            rating = rating,
            maxRating = 5,
            onStarClick = {
                rating = it.toFloat()
            }, false
        )

        var text by remember { mutableStateOf("") }

        GGTextField(
            text = text,
            onValueChange = {
                text = it
            },
            hint = "Write Your Review",
            textStyle = TextStyle(
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp,
            ),
            modifier = Modifier
                .padding(16.dp),
            focusedBorderColor = Color.Transparent,
            textFieldModifier = Modifier
                .fillMaxHeight(.3f)
                .fillMaxWidth(),
            shapeRadius = RoundedCornerShape(20.dp),
            singleLine = false
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            GGButton(
                title = "Send",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(.3f)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
private fun SummarizeComposable() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_summarize),
            contentDescription = "summarize_icon",
            modifier = Modifier.padding(top = 45.dp)
        )

        Text(
            text = stringResource(id = R.string.nothing_to_show),
            style = Theme.typography.bodyLarge,
            color = Theme.colors.primary
        )

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.click_summarize),
            style = Theme.typography.labelLarge,
            textAlign = TextAlign.Center,
            color = Theme.colors.primary
        )

        GGButton(
            title = "Summarize",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        )

    }
}

