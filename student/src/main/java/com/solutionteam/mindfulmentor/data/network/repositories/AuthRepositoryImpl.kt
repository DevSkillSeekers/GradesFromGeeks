package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    override suspend fun signUp(email: String, password: String): AuthResult = suspendCancellableCoroutine { continuation ->
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result!!)
                } else {
                    continuation.resumeWithException(task.exception ?: Exception("Unknown registration error"))
                }
            }
    }
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