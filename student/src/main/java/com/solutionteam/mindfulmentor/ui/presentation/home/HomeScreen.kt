package com.solutionteam.mindfulmentor.ui.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGSubject
import com.solutionteam.design_system.components.GGTextChipStyle
import com.solutionteam.design_system.components.GGTitleWithSeeAll
import com.solutionteam.design_system.components.GGUniversity
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.presentation.home.component.ChatBot
import com.solutionteam.mindfulmentor.ui.presentation.home.component.HomeAppBar
import com.solutionteam.mindfulmentor.ui.presentation.home.component.InComingMeeting
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    HomeContent(state = state)

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }

    LaunchedEffect(Unit) {
        while (state.upComingMeetings.isNotEmpty()) {
            viewModel.updateMeeting()
            delay(60_000)
        }
    }
}


private fun onEffect(effect: HomeUIEffect?, context: Context) {

    when (effect) {
        HomeUIEffect.HomeError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun HomeContent(state: HomeUIState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        HomeAppBar(
            modifier = Modifier.fillMaxWidth(),
            onNotificationClicked = {}
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {

                ChatBot(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {}
                )

                state.upComingMeetings.forEach { meeting ->
                    InComingMeeting(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        meeting = meeting,
                        onJoinClicked = {}
                    )
                }

                GGTitleWithSeeAll(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .padding(horizontal = 16.dp),
                    title = stringResource(id = R.string.mentors),
                    onClick = {}
                )

                state.mentors.take(3).forEach { mentor ->
                    GGMentor(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .padding(horizontal = 16.dp),
                        name = mentor.name,
                        rate = mentor.rate,
                        numberReviewers = mentor.numberReviewers,
                        profileUrl = mentor.imageUrl,
                        onClick = {}
                    )
                }

                GGTitleWithSeeAll(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 10.dp)
                        .padding(horizontal = 16.dp),
                    title = stringResource(id = R.string.subjects),
                    onClick = {}
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.subjects) { subject ->
                        GGSubject(
                            modifier = Modifier.width(100.dp),
                            name = subject.name,
                            onClick = {}
                        )
                    }
                }

                if (state.university.isNotEmpty()) {
                    GGTitleWithSeeAll(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 10.dp)
                            .padding(horizontal = 16.dp),
                        title = stringResource(id = R.string.universities),
                        onClick = {}
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.university) { university ->
                        GGUniversity(
                            modifier = Modifier.size(height = 215.dp, width = 322.dp),
                            name = university.name,
                            address = university.address,
                            imageUrl = university.imageUrl,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}





