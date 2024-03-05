package com.solutionteam.mentor.ui.wallet

import com.solutionteam.mentor.data.repositories.MindfulMentorRepository
import com.solutionteam.mentor.ui.base.BaseViewModel

class WalletViewModel(
    private val mindfulMentor: MindfulMentorRepository
) : BaseViewModel<WalletUIState, WalletUIEffect>(WalletUIState()) {


}
