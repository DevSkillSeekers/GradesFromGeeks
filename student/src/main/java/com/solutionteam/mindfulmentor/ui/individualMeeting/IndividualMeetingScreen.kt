package com.solutionteam.mindfulmentor.ui.individualMeeting

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGTextChipStyle
import com.solutionteam.design_system.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun IndividualMeetingScreen(
    navigateTo: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: IndividualMeetingViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    IndividualMeetingContent(state = state, onBack = navigateBack, navigateTo = navigateTo)

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: IndividualMeetingUIEffect?, context: Context) {

    when (effect) {
        IndividualMeetingUIEffect.IndividualMeetingError -> Toast.makeText(
            context,
            "error",
            Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IndividualMeetingContent(
    state: IndividualMeetingUIState,
    onBack: () -> Unit,
    navigateTo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        GGAppBar(modifier = Modifier.fillMaxWidth(), title = "One-One", onBack = onBack)

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {

            Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                state.availableDates.forEach { day ->
                    AvailableTimePerDay(
                        day = day
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailableTimePerDay(
    day: AvailableDateUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = day.day,
            textAlign = TextAlign.Start,
            style = Theme.typography.bodyLarge
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            day.times.forEach { time ->
                GGTextChipStyle(
                    value = time.time,
                    backgroundColor = Theme.colors.card,
                    paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}





