package com.solutionteam.mentor.ui.wallet


sealed interface WalletUIEffect {
    data object WalletError : WalletUIEffect
}
