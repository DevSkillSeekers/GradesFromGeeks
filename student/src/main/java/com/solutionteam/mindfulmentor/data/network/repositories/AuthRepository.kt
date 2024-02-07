package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Task<AuthResult>
    suspend fun signIn(email: String, password: String): AuthResult
}