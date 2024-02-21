package com.solutionteam.mindfulmentor.ui.downloads

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.mentor.composable.ContentCountCard
import com.solutionteam.mindfulmentor.ui.mentor.composable.MentorTabBar
import com.solutionteam.mindfulmentor.ui.mentor.composable.SubjectComposable
import com.solutionteam.mindfulmentor.ui.university.ContentCountUIState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadsScreen(
    viewModel: DownloadsViewModel = koinViewModel(),
    onNavigateTo: (DownloadsUIEffect) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    DownloadContent(
        state = state,
        onNavigateToReviewScreen = { onNavigateTo(DownloadsUIEffect.NavigateToReviewScreen) },
        onNavigateToPDFReader = { onNavigateTo(DownloadsUIEffect.NavigateToPDFReader) }
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: DownloadsUIEffect?, context: Context) {

    when (effect) {
        DownloadsUIEffect.DownloadsError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT)
            .show()

        else -> {}
    }
}


@Composable
private fun DownloadContent(
    state: DownloadsUIState,
    onNavigateToReviewScreen: () -> Unit,
    onNavigateToPDFReader: () -> Unit
) {

    Scaffold(
        topBar = {
            GGAppBar(
                title = stringResource(id = R.string.download_title),
                showNavigationIcon = false
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
                .background(Theme.colors.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    ContentCountCard(
                        contentCountList = listOf(
                            ContentCountUIState("10", "Mentors"),
                            ContentCountUIState("20", "Summaries"),
                            ContentCountUIState("30", "Videos")
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    SubjectComposable()
                    MentorTabBar(
                        nameTabs = listOf("Summaries", "Videos", "Meetings"),
                        onClickMeeting = onNavigateToReviewScreen,
                        onOpenPDFClicked = onNavigateToPDFReader
                    )
                }
            }
        }
    }
}
