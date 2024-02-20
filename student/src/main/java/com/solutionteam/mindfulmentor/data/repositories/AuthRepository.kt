package com.solutionteam.mindfulmentor.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.solutionteam.mindfulmentor.data.entity.StudentInfo
import com.solutionteam.mindfulmentor.data.utils.UserAlreadyExistsException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthResult

    suspend fun addStudentInfo(studentInfo: StudentInfo,user: FirebaseUser):Boolean

    suspend fun signIn(email: String, password: String): AuthResult
}

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    private val db = FirebaseFirestore.getInstance()

    override suspend fun signUp(
        email: String,
        password: String,
    ): AuthResult {
        return try {
            suspendCancellableCoroutine { continuation ->
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            continuation.resume(task.result!!)
                        } else {
                            continuation.resumeWithException(
                                task.exception ?: Exception("Unknown registration error")
                            )
                        }
                    }
            }
        }catch (e: FirebaseAuthUserCollisionException){
            throw UserAlreadyExistsException(e.message?:"")
        }
    }

    override suspend fun addStudentInfo(studentInfo: StudentInfo, user: FirebaseUser): Boolean {
        val result = db.collection("users").document(user.uid).set(studentInfo)
        return result.isSuccessful
    }

    override suspend fun signIn(email: String, password: String): AuthResult =
        suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(task.result!!)
                    } else {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Unknown authentication error")
                        )
                    }
                }
        }
}
