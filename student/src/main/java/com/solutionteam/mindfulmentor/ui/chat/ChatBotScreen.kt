package com.solutionteam.mindfulmentor.ui.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.solutionteam.design_system.components.GGDropdownMenu
import com.solutionteam.design_system.modifier.noRippleEffect
import com.solutionteam.design_system.theme.Gray_1
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R
import com.solutionteam.mindfulmentor.ui.chat.composable.MessageCard
import com.solutionteam.mindfulmentor.ui.chat.composable.SendTextField
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun ChatBotScreen(
    viewModel: ChatBotViewModel = koinViewModel(),
    onNavigateBack:()->Unit,
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)

    ChatBotContent(
            state = state,
            messageText = state.message,
            onValueChanged = viewModel::onChanceMessage,
            sendMessage = viewModel::onSendClicked,
            onCLickBack = onNavigateBack,
            onSelectUniversity = viewModel::onSelectUniversity
    )

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ChatBotContent(
    state: ChatUiState,
    messageText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    onCLickBack: () -> Unit,
    onSelectUniversity: (Int,String,String) -> Unit,
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current


    Scaffold(
            topBar = {
                TopAppBar(
                        modifier = Modifier.shadow(1.dp),
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = { onCLickBack() }) {
                                Icon(
                                        painterResource(com.solutionteam.design_system.R.drawable.arrow),
                                        contentDescription = "Go back",
                                        tint = Color.Unspecified,
                                        modifier = Modifier
                                            .noRippleEffect { onCLickBack() }
                                            .rotate(180f)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Theme.colors.background
                        ),
                        actions = {
                            if(!state.isFirstEnter){
                                GGDropdownMenu(
                                        modifier = Modifier
                                            .width(200.dp),
                                        items = state.universities,
                                        selectedIndex = state.selectedUniversity,
                                        onItemSelected = { index, university ->
                                            Log.e("TAG", "ChatBotContent: $university")
                                            val user = context.getString(R.string.user_role, university)
                                            val model = context.getString(R.string.model_role, university)
                                            onSelectUniversity(index,user,model)
                                        },
                                        placeholder = "Select University",
                                        colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                disabledContainerColor = Color.Transparent,
                                                cursorColor = Gray_1,
                                                errorCursorColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedBorderColor = Color.Transparent,
                                                errorBorderColor = Color.Transparent,
                                        )
                                )
                            }
                        }
                )
            }
    ) {padding->
        Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                ) {
            Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.background_chat_screen),
                    contentDescription = "background chat screen",
                    contentScale = ContentScale.Crop,
            )
            AnimatedVisibility(visible = state.isFirstEnter) {
                Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(0.7f),
                ) {
                    Box(
                            modifier = Modifier
                                .size(150.dp)
                                .background(color = Theme.colors.card, shape = CircleShape),
                            contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                                painter = painterResource(id = R.drawable.chat_bot_background),
                                contentDescription = "icon chat bot",
                                modifier = Modifier.padding(top = 16.dp),
                                tint = Theme.colors.primary
                        )
                    }
                    Text(
                            text = stringResource(R.string.start_chatting_with_chat_bot_now),
                            style = Theme.typography.titleSmall,
                            color = Theme.colors.primaryShadesDark,
                            modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                            text = stringResource(R.string.by_select_the_university_you_want_to_learn_more_about_and_obtain_references_from),
                            style = Theme.typography.bodyLarge,
                            color = Theme.colors.ternaryShadesDark,
                            modifier = Modifier.padding(top = 8.dp, end = 38.dp, start = 38.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    GGDropdownMenu(
                            modifier = Modifier
                                .width(250.dp)
                                .padding(top = 28.dp),
                            items = state.universities,
                            placeholder = "Select University",
                            selectedIndex = state.selectedUniversity,
                            onItemSelected = { index, university ->
                                Log.e("TAG", "ChatBotContent: $university")
                                val user = context.getString(R.string.user_role, university)
                                val model = context.getString(R.string.model_role, university)
                                onSelectUniversity(index,user,model)
                            },
                    )
                }
            }
            AnimatedVisibility(visible = !state.isFirstEnter) {
                Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(
                            state = rememberLazyListState(),
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Bottom,
                            contentPadding = PaddingValues(vertical = 24.dp),
                    ) {

                        item {
                            Text(
                                    text = state.universities[state.selectedUniversity],
                                    style = Theme.typography.bodyLarge,
                                    color = Theme.colors.ternaryShadesDark,
                                    modifier = Modifier
                                        .padding(top = 8.dp, end = 38.dp, start = 38.dp)
                                        .fillMaxWidth(),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }

                        items(items = state.messages) {
                            MessageCard(it, Modifier.animateItemPlacement())
                        }

                    }
                    SendTextField(
                            text = messageText,
                            onValueChanged = onValueChanged,
                            sendMessage = sendMessage,
                            canMessage = state.canNotSendMessage
                    )
                }
            }
        }

    }

    LaunchedEffect(key1 = true) {
        listState.animateScrollToItem(index = state.messages.lastIndexOrZero()  )
    }

}

fun <T> List<T>.lastIndexOrZero() : Int {
    return if (this.isEmpty()) 0 else this.size - 1
}
