package com.example.login.data.service

import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.responses.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://mydomain.com"
const val EMAIL = "email"

interface LoginService {

    @POST("$BASE_URL/Auth/Login")
    fun login(
        @Body loginRequestBody: LoginRequestBody
    ): Deferred<Response<LoginResponse>>

    @POST("$BASE_URL/Auth/ForgetPassword")
    fun forgetPassword(
        @Query(EMAIL) email: String
    ): Deferred<Response<Unit>>
}