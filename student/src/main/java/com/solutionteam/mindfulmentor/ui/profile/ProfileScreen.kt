package com.solutionteam.mindfulmentor.ui.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.components.GGBottomSheet
import com.solutionteam.design_system.components.GGPreferencesCard
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.design_system.theme.mindfulMentorTypography
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
        state = state,
        onDismissThemeRequest = viewModel::onDismissThemeRequest,
        onThemeClicked = viewModel::onThemeClicked,
        onThemeChanged = viewModel::onThemeChanged
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileContent(
    state: ProfileUIState,
    onDismissThemeRequest: () -> Unit,
    onThemeClicked: () -> Unit,
    onThemeChanged: (Boolean) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
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
                onClick = onThemeClicked
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

        if (state.showBottomSheetThem) {
            GGBottomSheet(
                sheetState = sheetState,
                onDismissRequest = onDismissThemeRequest
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Choose your theme",
                    style = mindfulMentorTypography.titleSmall,
                    color = Theme.colors.primaryShadesDark
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Image(
                        painterResource(id = R.drawable.theme_light),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .then(
                                if (!state.isDarkTheme) {
                                    Modifier.border(
                                        width = 2.dp,
                                        color = Theme.colors.primary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                } else {
                                    Modifier
                                }
                            )
                            .noRippleEffect { onThemeChanged(false) },
                        contentScale = ContentScale.FillWidth
                    )

                    Image(
                        painterResource(id = R.drawable.frame_theme_dark),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .then(
                                if (state.isDarkTheme) {
                                    Modifier.border(
                                        width = 2.dp,
                                        color = Theme.colors.primary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                } else {
                                    Modifier
                                }
                            )
                            .noRippleEffect { onThemeChanged(true) },
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}




