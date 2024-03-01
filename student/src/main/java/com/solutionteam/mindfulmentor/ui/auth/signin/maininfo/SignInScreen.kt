package com.solutionteam.mindfulmentor.ui.auth.signin.maininfo

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.components.GGBackTopAppBar
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGSnackbar
import com.solutionteam.design_system.components.GGTextField
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.auth.composables.TextWithClick
import com.solutionteam.mindfulmentor.ui.auth.signin.SignUpUiState
import com.solutionteam.mindfulmentor.ui.auth.signin.SignInViewModel
import com.solutionteam.mindfulmentor.ui.auth.signin.additionalinfo.AdditionalInformationScreenContent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.annotations.ApiStatus.Internal
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    navigateTo: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                Log.e("TAG", "SignInScreen: launcher resultCode ${result.resultCode}")

                if(result.resultCode == RESULT_OK) {
                    Log.e("TAG", "resultCode == RESULT_OK")
                    viewModel.onGoogleSignInResult(result.data)
                }
            }
    )
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest {
            onEffect(effect, context, launcher)
        }
    }

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

    if (state.isScreenContinue) {
        SignInScreenContent(
            state = state,
            onNavigateBack = onNavigateBack,
            onChangeEmail = viewModel::onChangeEmail,
            onChangeUserName = viewModel::onChangeUserName,
            onChangePassword = viewModel::onChangePassword,
            onClickContinue = viewModel::onClickContinue,
            snackbarHostState = snackbarHostState,
            onClickGoogle = viewModel::onClickGoogle,
            onClickFacebook = {
            //    viewModel::onClickFacebook
            }
        )
    } else {
        AdditionalInformationScreenContent(
            state = state,
            onSignInClick = viewModel::onClickSignUp,
            onNavigateBack = viewModel::onClickBackInAdditionalInfo,
            snackbarHostState = snackbarHostState,
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
fun onEffect(effect: SignInUIEffect?, context: Context, launcher: ActivityResultLauncher<IntentSenderRequest>) {

    when (effect) {
        is SignInUIEffect.GoogleSignIn -> {
            val signInIntentSender = effect.intentSender
            launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
        }
        else -> {}
    }

}

@Composable
fun SignInScreenContent(
    state: SignUpUiState,
    onNavigateBack: () -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeUserName: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onClickContinue: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onClickGoogle:() -> Unit,
    onClickFacebook:() -> Unit
) {
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text(
                    text = "Sign UP",
                    style = Theme.typography.titleLarge,
                    color = Theme.colors.primaryShadesDark,
                    fontSize = 30.sp
                )
                GGTextField(label = "Email", text = state.email, onValueChange = onChangeEmail)
                GGTextField(
                    label = "User Name",
                    text = state.userName,
                    onValueChange = onChangeUserName
                )
                GGTextField(
                    label = "Your PassWord",
                    text = state.password, onValueChange = onChangePassword,
                    keyboardType = KeyboardType.Password
                )
                GGButton(
                    title = "Continue",
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth()
                )
                SignWithOtherWays(
                    onClickGoogle = onClickGoogle,
                    onClickFacebook = onClickFacebook
                )
            }
        }

    }
    GGSnackbar(snackbarHostState = snackbarHostState)
}

@Composable
fun SignWithOtherWays(
    onClickGoogle:() -> Unit,
    onClickFacebook:() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "- Or -",
            style = Theme.typography.titleSmall,
            color = Theme.colors.ternaryShadesDark
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IconButton(onClick = onClickGoogle) {
                Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = ""
                )
            }
            IconButton(onClick = onClickFacebook) {
                Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = ""
                )
            }
        }
        TextWithClick(
            fullText = "Don't have an account? signUp",
            linkText = "signUp",
            onClick = {}
        )
    }
}
