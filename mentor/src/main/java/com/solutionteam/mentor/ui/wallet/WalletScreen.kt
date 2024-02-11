package com.solutionteam.mentor.ui.wallet

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = koinViewModel(),
    navigateTo: (WalletUIEffect) -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    WalletContent()

}


private fun onEffect(effect: WalletUIEffect?, context: Context) {

    when (effect) {
        WalletUIEffect.WalletError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}

@Composable
fun WalletContent() {

    Text(text = "Wallet")
}
