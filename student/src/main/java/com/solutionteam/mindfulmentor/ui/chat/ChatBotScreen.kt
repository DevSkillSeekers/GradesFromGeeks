package com.solutionteam.mindfulmentor.ui.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.solutionteam.design_system.modifier.noRippleEffect
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
    val context = LocalContext.current
    LaunchedEffect(key1 = Lifecycle.State.RESUMED) {
        viewModel.setRoles(
                user = context.getString(R.string.userRole),
                model = context.getString(R.string.modelRole)
        )
    }
    ChatBotContent(
            state = state,
            messageText = state.message,
            onValueChanged = viewModel::onChanceMessage,
            sendMessage = viewModel::onSendClicked,
            onCLickBack = onNavigateBack,
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
) {
    val listState = rememberLazyListState()

    Scaffold(
            topBar = {
                TopAppBar(
                        modifier = Modifier.shadow(1.dp),
                        title = {
                            Text(
                                    text = "Chat Bot",
                                    style = Theme.typography.labelLarge,
                            )
                        },
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
                        )
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
            Column(
                    verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Bottom,
                        contentPadding = PaddingValues(vertical = 24.dp),
                ) {
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

    LaunchedEffect(key1 = true) {
        listState.animateScrollToItem(index = state.messages.lastIndexOrZero()  )
    }

}

fun <T> List<T>.lastIndexOrZero() : Int {
    return if (this.isEmpty()) 0 else this.size - 1
}
