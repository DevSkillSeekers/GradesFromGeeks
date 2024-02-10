package com.solutionteam.mentor.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateTo: (HomeUIEffect) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    HomeContent()

    LaunchedEffect(key1 = !state.isLoading && !state.isError) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
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
private fun HomeContent() {
    Text(text = "Home")
}


