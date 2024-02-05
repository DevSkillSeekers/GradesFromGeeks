package com.solutionteam.mindfulmentor.ui.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGMentor
import com.solutionteam.design_system.components.GGSubject
import com.solutionteam.design_system.components.GGTitleWithSeeAll
import com.solutionteam.design_system.components.GGUniversity
import com.solutionteam.design_system.components.Loading
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.home.HomeUIEffect
import com.solutionteam.mindfulmentor.ui.home.showSeeAll
import com.solutionteam.mindfulmentor.ui.search.composable.EmptySearchItem
import com.solutionteam.mindfulmentor.ui.search.composable.SearchTextField
import com.solutionteam.mindfulmentor.ui.seeAll.SeeAllType
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navigateTo: (SearchUIEffect) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    SearchContent(
            state = state,
            onSearchValueChanged = viewModel::onSearchTextChange,
            onClickUniversity = { navigateTo(SearchUIEffect.NavigateToUniversityProfile) },
            onClickMentor = { navigateTo(SearchUIEffect.NavigateToMentorProfile)},
            onClickSeeAllUniversities = { navigateTo(SearchUIEffect.NavigateToSeeAll(SeeAllType.Universities)) },
            onClickSeeAllPeople = { navigateTo(SearchUIEffect.NavigateToSeeAll(SeeAllType.Mentors)) },
            onClickSubject = {navigateTo(SearchUIEffect.NavigateToSubject) },
            onClickSeeAllSubject ={ navigateTo(SearchUIEffect.NavigateToSeeAll(SeeAllType.Subjects)) }
    )

}


private fun onEffect(effect: SearchUIEffect?, context: Context) {

    when (effect) {
        SearchUIEffect.SearchError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}

@Composable
fun SearchContent(
    state: SearchUIState,
    onSearchValueChanged: (String) -> Unit,
    onClickUniversity: (String) -> Unit,
    onClickMentor: (String) -> Unit,
    onClickSubject: (String) -> Unit,
    onClickSeeAllUniversities: () -> Unit,
    onClickSeeAllSubject: () -> Unit,
    onClickSeeAllPeople: () -> Unit,
) {

    Scaffold(
            modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
                modifier = Modifier
                    .padding(PaddingValues(top = padding.calculateTopPadding()))
                    .background(Theme.colors.background)
        ) {
            SearchTextField(
                    text = state.keyword,
                    onValueChanged = onSearchValueChanged,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            Divider(
                    modifier = Modifier
                        .shadow(
                                1.dp,
                                ambientColor = Color.LightGray,
                                spotColor = Color.LightGray
                        )
                        .fillMaxWidth(),
                    color = Color.Transparent
            )
            if (state.isLoading) {
                Loading()
            } else if (state.mentors.isEmpty() && state.universities.isEmpty()) {
                EmptySearchItem()
            } else {

                Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(state = rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    if (state.mentors.isNotEmpty()) {
                        GGTitleWithSeeAll(
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .padding(horizontal = 16.dp),
                                title = stringResource(id = R.string.mentors),
                                showSeeAll = state.mentors.showSeeAll(),
                                onClick = onClickSeeAllPeople
                        )


                        state.mentors.take(3).forEach { mentor ->
                            GGMentor(modifier = Modifier
                                .padding(vertical = 4.dp)
                                .padding(horizontal = 16.dp),
                                    name = mentor.name,
                                    rate = mentor.rate,
                                    numberReviewers = mentor.numberReviewers,
                                    profileUrl = mentor.imageUrl,
                                    onClick = { onClickMentor(mentor.id) })
                        }
                    }
                    if (state.mentors.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    if (state.universities.isNotEmpty()) {
                        GGTitleWithSeeAll(
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .padding(horizontal = 16.dp),
                                title = stringResource(id = R.string.universities),
                                showSeeAll = state.universities.showSeeAll(),
                                onClick = onClickSeeAllUniversities
                        )
                        LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(state.universities) { university ->
                                GGUniversity(
                                        modifier = Modifier.size(height = 215.dp, width = 322.dp),
                                        name = university.name,
                                        address = university.address,
                                        imageUrl = university.imageUrl,
                                        onClick = { onClickUniversity(university.id) }
                                )
                            }
                        }
                    }

                    if (state.universities.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    if (state.subjects.isNotEmpty()) {
                        GGTitleWithSeeAll(
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .padding(horizontal = 16.dp),
                                title = stringResource(id = R.string.subjects),
                                showSeeAll = state.subjects.showSeeAll(),
                                onClick = onClickSeeAllSubject
                        )

                        LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(state.subjects) { subject ->
                                GGSubject(
                                        modifier = Modifier.width(100.dp),
                                        name = subject.name,
                                        onClick = { onClickSubject(subject.id) }
                                )
                            }
                        }

                    }

                }
            }
        }


    }
}
