package com.solutionteam.mindfulmentor.ui.auth.signin.additionalinfo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.components.GGBackTopAppBar
import com.solutionteam.design_system.components.GGBottomSheetWithSearch
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGDropdownMenu
import com.solutionteam.design_system.components.GGSnackbar
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.auth.signin.SignUpUiState
import com.solutionteam.mindfulmentor.ui.auth.signin.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun AdditionalInformationScreen(
    viewModel: SignInViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    navigateTo: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    AdditionalInformationScreenContent(
        state = state,
        onSignInClick = viewModel::onClickSignUp,
        onNavigateBack = onNavigateBack,
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

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditionalInformationScreenContent(
    state: SignUpUiState,
    onSignInClick: () -> Unit,
    onNavigateBack: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onChangeLevel: (Int) -> Unit,
    onChangeUniversity: (String) -> Unit,
    onChangeField: (String) -> Unit,
    universitiesNames: List<String>,
    fields: List<String>,
    levels: List<Int>,
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val universityBottomSheetState = rememberBottomSheetScaffoldState()
    val fieldBottomSheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        GGBackTopAppBar(onNavigateBack)
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
                GGBottomSheetWithSearch(
                    items = universitiesNames,
                    onItemSelected = onChangeUniversity,
                    bottomSheetState = universityBottomSheetState
                ) {
                    GGButton(
                        title = state.universityName,
                        onClick = {
                            scope.launch {
                                universityBottomSheetState.bottomSheetState.expand()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Theme.colors.card
                    )
                }
                GGBottomSheetWithSearch(
                    items = fields,
                    onItemSelected = onChangeField,
                    bottomSheetState = fieldBottomSheetState,
                ) {
                    GGButton(
                        title = state.field,
                        onClick = {
                            scope.launch {
                                fieldBottomSheetState.bottomSheetState.expand()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Theme.colors.card
                    )
                }
                GGDropdownMenu(
                    label = "Field",
                    items = listOf("Cs", "Art", "Engineering"),
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, item ->
                        selectedIndex = index
                        onChangeUniversity(item)
                    },
                )
                GGDropdownMenu(
                    label = "Level",
                    items = listOf(1, 2, 3, 4),
                    selectedIndex = selectedIndex,
                    onItemSelected = { index, item ->
                        selectedIndex = index
                        onChangeLevel(item)
                    },
                )
                GGButton(
                    title = "Create Account",
                    onClick = onSignInClick,
                    modifier = Modifier.fillMaxWidth()
                )
                GGSnackbar(snackbarHostState = snackbarHostState)
            }
        }
    }
}
