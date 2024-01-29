package com.solutionteam.mindfulmentor.ui.presentation.auth.signin

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.identity.Identity
import com.solutionteam.design_system.components.GGBackTopAppBar
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGDropdownMenu
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.data.network.repositories.GoogleAuthUiClient
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun AdditionalInformationScreen(
    viewModel: SignInViewModel = koinViewModel(),
    navigateTo: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    AdditionalInformationScreenContent(
        state = state,
        onSignInClick = {
            coroutineScope.launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }
    )

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(
                context, "Sign in successful", Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun AdditionalInformationScreenContent(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        GGBackTopAppBar({})
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
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
                GGDropdownMenu(
                    label = "University",
                    items = listOf("Alexandria University", "Cairo University", "Helwan University"),
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, _ -> selectedIndex = index },
                )
                GGDropdownMenu(
                    label = "Field",
                    items = listOf("Cs", "Art", "Engineering"),
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, _ -> selectedIndex = index },
                )
                GGDropdownMenu(
                    label = "Level",
                    items = listOf("Item 1", "Item 2", "Item 3"),
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, _ -> selectedIndex = index },
                )
                GGButton(
                    title = "Create Account",
                    onClick = onSignInClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
