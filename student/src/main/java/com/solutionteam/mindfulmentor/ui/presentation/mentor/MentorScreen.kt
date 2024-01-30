package com.solutionteam.mindfulmentor.ui.presentation.mentor

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.solutionteam.design_system.components.GGAppBar
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun MentorScreen(
    viewModel: MentorViewModel = koinViewModel(),
    onNavigateTo: () -> Unit,
    navigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    MentorContent(
        state = state,
        onBack = navigateBack
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: MentorUIEffect?, context: Context) {

    when (effect) {
        MentorUIEffect.MentorError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun MentorContent(
    state: MentorUIState,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        GGAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.mentors),
            onBack = onBack
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "Mentor screen",
                style = Theme.typography.labelMedium,
                color = Theme.colors.primary
            )
        }
    }

}
