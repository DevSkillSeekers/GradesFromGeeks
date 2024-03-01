package com.solutionteam.mindfulmentor.ui.auth.signin.maininfo

import android.content.Intent
import android.content.IntentSender

interface SignInUIEffect {
    object SignInError : SignInUIEffect
    data class GoogleSignIn(val intentSender: IntentSender) : SignInUIEffect
}