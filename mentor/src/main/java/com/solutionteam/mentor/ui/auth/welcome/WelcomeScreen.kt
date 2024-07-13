package com.solutionteam.mentor.ui.auth.welcome

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mentor.R
import com.solutionteam.mentor.ui.auth.composables.TextWithClick
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinViewModel(),
    navigateTo: (WelcomeUiEffect?) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    WelcomeScreenContent(
        state = state,
        onClickLogin = viewModel::onClickLogin,
        onClickSignIn = viewModel::onClickSignIn,
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context, navigateTo)
        }
    }
}

@Composable
private fun WelcomeScreenContent(
    state: WelcomeUiState,
    onClickSignIn: () -> Unit,
    onClickLogin: () -> Unit,
) {
    if (state.isLoading) {

        CircularProgressIndicator()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(vertical = 40.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.signup_or_login),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            GGButton(
                title = "Sign Up",
                onClick = onClickSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            GGButton(
                title = "Log In",
                onClick = onClickLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                containerColor = Theme.colors.card,
                contentColor = Theme.colors.primaryShadesDark
            )

            TextWithClick(
                fullText = stringResource(R.string.services_text),
                linkText = stringResource(R.string.services_text_link),
                url = stringResource(R.string.google_link),
                modifier = Modifier.padding(bottom = 47.dp, start = 39.dp, end = 39.dp)
            )

        }
    }
}

private fun onEffect(
    effect: WelcomeUiEffect?,
    context: Context, navigateTo: (WelcomeUiEffect?) -> Unit
) {
    when (effect) {
        is WelcomeUiEffect.WelcomeError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        is WelcomeUiEffect.OnClickLogin -> navigateTo(WelcomeUiEffect.OnClickLogin)
        is WelcomeUiEffect.OnClickSignIn -> navigateTo(WelcomeUiEffect.OnClickSignIn)
        else -> {}
    }
}