package com.solutionteam.mindfulmentor.ui.presentation.auth.welcome

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinViewModel(),
    navigateTo: () -> Unit
){
    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}

private fun onEffect(effect: WelcomeUiEffect?, context: Context) {

    when (effect) {
        WelcomeUiEffect.WelcomeError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}

@Composable
fun WelcomeScreenContent(
    state: WelcomeUiState,
    navigateTo: () -> Unit
){
    if (state.isLoading) {
        CircularProgressIndicator()
    }else{
        Column(
            modifier = Modifier.fillMaxSize(),

        ) {

        }
    }
}