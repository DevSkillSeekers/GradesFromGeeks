package com.solutionteam.mindfulmentor.ui.review

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGTabBar
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.mentor.composable.SummeryScreen
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
                        "Info" to { },
                        "Review" to { },
                        "Summarize" to { SummeryScreen() }
                    ),
                )
            }
        }
    }
}