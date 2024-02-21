package com.solutionteam.mindfulmentor.ui.individualMeeting

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.setStatusBarColor
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.individualMeeting.composable.AvailableTimePerDay
import com.solutionteam.mindfulmentor.ui.individualMeeting.composable.ScheduleMeetingBottomSheet
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

    IndividualMeetingContent(
        state = state,
        onBack = navigateBack,
        onTimeSelected = viewModel::onTimeSelected,
        onDismissRequest = viewModel::onDismissRequest,
        onValueChange = viewModel::onValueChange,
        onBookClick = viewModel::onBookClick
    )

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }

    val systemUIController = rememberSystemUiController()
    val color = Theme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            navigationBarColor = color,
        )
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


@Composable
private fun IndividualMeetingContent(
    state: IndividualMeetingUIState,
    onBack: () -> Unit,
    onTimeSelected: (TimeUiState) -> Unit,
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    onBookClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background)
            .padding(WindowInsets.navigationBars.asPaddingValues()),
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
                        day = day,
                        onTimeSelected = onTimeSelected
                    )
                }
            }
        }

        if (state.showBottomSheet && state.selectedTime != null) {
            ScheduleMeetingBottomSheet(
                onDismissRequest = onDismissRequest,
                timeUiState = state.selectedTime,
                note = state.note,
                onValueChange = onValueChange,
                onBookClick = onBookClick
            )
        }
    }
}







