package com.solutionteam.mindfulmentor.data.network.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.solutionteam.mindfulmentor.data.entity.StudentInfo

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthResult

    suspend fun addStudentInfo(studentInfo: StudentInfo,user: FirebaseUser):Boolean

    suspend fun signIn(email: String, password: String): AuthResult
}