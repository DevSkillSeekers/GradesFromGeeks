package com.solutionteam.mindfulmentor.data.repositories

import android.util.Log
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.solutionteam.mindfulmentor.data.entity.StudentInfo
import com.solutionteam.mindfulmentor.data.source.remote.response.UserData
import com.solutionteam.mindfulmentor.data.utils.UserAlreadyExistsException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import okhttp3.internal.userAgent
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface AuthRepository {
    suspend fun signOut()
    suspend fun signUp(email: String, password: String,studentInfo:StudentInfo): Boolean

    suspend fun addStudentInfo(studentInfo: StudentInfo, userId: String): Boolean
    suspend fun getSignedInUser(): UserData?

    suspend fun signIn(email: String, password: String): Boolean
    suspend fun checkUserExist(email: String?): Boolean
}

class AuthRepositoryImpl(
    private val oneTapClient: SignInClient
) : AuthRepository {
    private val auth = Firebase.auth
    private val db = FirebaseFirestore.getInstance()

   override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    override suspend fun getSignedInUser(): UserData? {
      val result = auth.currentUser?.run {
            UserData(
                    userId = uid,
                    username = displayName,
                    profilePictureUrl = photoUrl?.toString(),
                    email = email
            )
        }
        Log.e("TAG", "getSignedInUser: $result")
        return result
    }
    override suspend fun signUp(
        email: String,
        password: String,
        studentInfo: StudentInfo
    ): Boolean {
        return try {
            suspendCancellableCoroutine { continuation ->
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            task.result.user?.let { user ->
                                db.collection("student").document(user.uid).set(studentInfo).addOnSuccessListener {
                                    continuation.resume(true)
                                }.addOnFailureListener {
                                    continuation.resume(false)
                                }
                            }
                        } else {
                            continuation.resumeWithException(
                                    task.exception ?: Exception("Unknown authentication error")
                            )
                        }
                    }
            }
        } catch (e: FirebaseAuthUserCollisionException) {
            throw UserAlreadyExistsException(e.message ?: "")
        }
    }

override suspend fun addStudentInfo(studentInfo: StudentInfo, userId: String): Boolean =
    suspendCancellableCoroutine { continuation ->
    val student = db.collection("student").document(userId)
    student.set(
            StudentInfo(
                    userName = studentInfo.userName,
                    universityName = studentInfo.universityName,
                    field = studentInfo.field,
                    level = studentInfo.level,
                    imageUrl = studentInfo.imageUrl,
                    email = studentInfo.email
            )
    ).addOnSuccessListener {
        continuation.resume(true)
    }.addOnFailureListener {
        continuation.resume(false)
    }
}

    override suspend fun signIn(email: String, password: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("TAG", "signIn: ${task.result.user}")
                        continuation.resume(task.result.user?.email == email)
                    } else {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Unknown authentication error")
                        )
                    }
                }
        }

    override suspend fun checkUserExist(email: String?): Boolean {
        return db.collection("student").whereEqualTo("email", email).get().await().isEmpty
    }
}
