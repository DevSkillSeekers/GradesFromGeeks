package com.solutionteam.mindfulmentor.ui.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.solutionteam.mindfulmentor.ui.components.GGButton
import com.solutionteam.mindfulmentor.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navigateTo: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    LoginContent(
        state = state,
        navigateTo = navigateTo
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: LoginUIEffect?, context: Context) {

    when (effect) {
        LoginUIEffect.LoginError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun LoginContent(
    state: LoginUIState,
    navigateTo: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

       if (state.isLoading){
           CircularProgressIndicator()
       }else{
           Text(
                   text = "login Screen",
                   style = Theme.typography.mainFontMedium,
                   color = Theme.colors.mainColor
           )

           GGButton(title = "Go to Home", onClick = navigateTo)
       }
    }

}