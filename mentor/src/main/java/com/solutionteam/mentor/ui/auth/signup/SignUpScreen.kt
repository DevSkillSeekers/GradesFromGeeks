package com.solutionteam.mentor.ui.auth.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.components.GGBackTopAppBar
import com.solutionteam.design_system.components.GGSnackbar
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mentor.R
import com.solutionteam.mentor.ui.auth.signup.component.SignUpFistStep
import com.solutionteam.mentor.ui.auth.signup.component.SignUpSecondStepScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    navigateTo: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state) {
        if (state.isSignInSuccessful) {
            Toast.makeText(
                context, "Sign in successful", Toast.LENGTH_LONG
            ).show()
            navigateTo()
        }
        if (state.errorMessage != null && state.isError) {
            val result = snackbarHostState.showSnackbar(
                message = state.errorMessage!!,
                actionLabel = "Hide",
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.Dismissed || result == SnackbarResult.ActionPerformed) {
                viewModel.clearErrorState()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            GGBackTopAppBar(onNavigateBack)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                text = stringResource(id = R.string.sign_up),
                style = Theme.typography.titleLarge,
                color = Theme.colors.primaryShadesDark,
                fontSize = 30.sp,
                textAlign = TextAlign.Start
            )

            if (state.isScreenContinue) {
                SignUpFistStep(
                    state = state,
                    onChangeEmail = viewModel::onChangeEmail,
                    onChangeUserName = viewModel::onChangeUserName,
                    onChangePassword = viewModel::onChangePassword,
                    onClickContinue = viewModel::onClickContinue,

                    )
            } else {
                SignUpSecondStepScreen(
                    state = state,
                    onSignInClick = viewModel::onClickSignUp,
                    onChangeField = viewModel::onChangeField,
                    onChangeLevel = viewModel::onChangeLevel,
                    onChangeUniversity = viewModel::onChangeUniversityName,
                    universitiesNames = listOf(
                        "Alexandria University",
                        "Harvard University",
                        "minia University"
                    ),
                    fields = listOf("engineering", "art", "science", "finance", "accounting"),
                    levels = listOf(1, 2, 3, 4, 5)
                )
            }
        }
    }

    GGSnackbar(snackbarHostState = snackbarHostState)
}



