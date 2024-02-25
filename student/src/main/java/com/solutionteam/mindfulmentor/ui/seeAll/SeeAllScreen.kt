package com.solutionteam.mindfulmentor.ui.seeAll

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGUniversity
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SeeAllScreen(
    type: SeeAllType,
    viewModel: SeeAllViewModel = koinViewModel(parameters = { parametersOf(type) }),
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    SeeAllContent(
        state = state,
        onBack = navigateBack,
        navigateTo = navigateTo
    )

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: SeeAllUIEffect?, context: Context) {

    when (effect) {
        SeeAllUIEffect.SeeAllError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun SeeAllContent(
    state: SeeAllUIState,
    onBack: () -> Unit,
    navigateTo: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        GGAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = when (state.type) {
                SeeAllType.Mentors -> stringResource(id = R.string.mentors)
                SeeAllType.Universities -> stringResource(id = R.string.universities)
                SeeAllType.Subjects -> stringResource(id = R.string.subjects)
            },
            onBack = onBack
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.mentors) { mentor ->
                    GGMentor(
                        modifier = Modifier.fillMaxWidth(),
                        name = mentor.name,
                        rate = mentor.rate,
                        numberReviewers = mentor.numberReviewers,
                        profileUrl = mentor.imageUrl,
                        onClick = { navigateTo(mentor.id) }
                    )
                }

                items(state.universities) { university ->
                    GGUniversity(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 215.dp),
                        name = university.name,
                        address = university.address,
                        imageUrl = university.imageUrl,
                        onClick = { navigateTo(university.id) }
                    )
                }
            }
        }
    }
}





