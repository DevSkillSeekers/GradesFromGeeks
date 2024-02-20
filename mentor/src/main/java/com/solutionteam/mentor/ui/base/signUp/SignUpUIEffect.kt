package com.solutionteam.mentor.ui.base.signUp

sealed interface SignUpUIEffect {
    data object ProfileError : SignUpUIEffect
}
