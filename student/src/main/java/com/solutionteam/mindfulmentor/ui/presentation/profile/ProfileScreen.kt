package com.solutionteam.mindfulmentor.ui.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGPreferencesCard
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.presentation.profile.component.ProfileCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    ProfileContent(
        state = state
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: ProfileUIEffect?, context: Context) {

    when (effect) {
        ProfileUIEffect.ProfileError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun ProfileContent(
    state: ProfileUIState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        GGAppBar(
            title = stringResource(id = R.string.profile_title),
            showNavigationIcon = false
        )

        ProfileCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            profileUrl = state.profileUrl,
            name = state.name
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.preferences),
            style = Theme.typography.titleSmall,
            color = Theme.colors.primaryShadesDark
        )

        GGPreferencesCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.theme),
            painter = painterResource(id = R.drawable.theme),
            onClick = {}
        )

        GGPreferencesCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.language),
            painter = painterResource(id = R.drawable.planet),
            onClick = {}
        )

        GGPreferencesCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.logout),
            painter = painterResource(id = R.drawable.logout),
            onClick = {}
        )
    }

}




