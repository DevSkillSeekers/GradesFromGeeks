package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    override suspend fun signUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun signIn(email: String, password: String): AuthResult = suspendCancellableCoroutine { continuation ->
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result!!)
                } else {
                    continuation.resumeWithException(task.exception ?: Exception("Unknown authentication error"))
                }
            }
    }
}