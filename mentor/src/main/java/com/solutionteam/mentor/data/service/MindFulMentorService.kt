package com.solutionteam.mentor.data.service

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET

interface MindFulMentorService {


    @GET("login/")
    suspend fun getLogin(
        @Field("phone_number") phoneNumber: String?,
        @Field("password") password: String?,
    ): Response<Any>

}
