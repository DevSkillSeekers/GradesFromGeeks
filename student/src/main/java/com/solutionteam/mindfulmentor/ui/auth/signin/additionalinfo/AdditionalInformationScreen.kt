package com.solutionteam.mindfulmentor.ui.auth.signin.additionalinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solutionteam.design_system.components.GGBackTopAppBar
import com.solutionteam.design_system.components.GGBottomSheetWithSearch
import com.solutionteam.design_system.components.GGButton
import com.solutionteam.design_system.components.GGDropdownMenu
import com.solutionteam.design_system.components.GGSnackbar
import com.solutionteam.design_system.components.GGToggleBottomSheetButton
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.ui.auth.signin.SignUpUiState

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
    var isUniversitySheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isFieldSheetOpen by rememberSaveable {
        mutableStateOf(false)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                GGBackTopAppBar(onNavigateBack)
                Text(
                    text = "Sign UP",
                    style = Theme.typography.titleLarge,
                    color = Theme.colors.primaryShadesDark,
                    fontSize = 30.sp
                )
                GGToggleBottomSheetButton(
                    value = state.universityName,
                    onValueChanged = {isUniversitySheetOpen = false},
                    isOpen = isUniversitySheetOpen,
                    onToggle = {isUniversitySheetOpen = true},
                    hint = "Select University",
                    label = "University"
                )
                GGToggleBottomSheetButton(
                    value = state.field,
                    onValueChanged = {isFieldSheetOpen = false},
                    isOpen = isFieldSheetOpen,
                    onToggle = {isFieldSheetOpen = true},
                    hint = "Select Field",
                    label = "Field"
                )

                if (isUniversitySheetOpen){
                GGBottomSheetWithSearch(
                    items = universitiesNames,
                    onItemSelected = {university, _ -> onChangeUniversity(university) },
                    onDismissRequest = {isUniversitySheetOpen = false}
                ) }
                if (isFieldSheetOpen){
                GGBottomSheetWithSearch(
                    items = fields,
                        onItemSelected = {field, _ -> onChangeField(field) },
                    onDismissRequest = {isFieldSheetOpen = false}
                )
                }
                GGDropdownMenu(
                    label = "Level",
                    items = levels,
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
