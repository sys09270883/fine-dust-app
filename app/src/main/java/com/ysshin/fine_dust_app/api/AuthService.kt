package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login/")
    fun login(@Body login: Login): Call<Auth>

    @POST("auth/logout/")
    fun logout()

    @POST("auth/registration/")
    fun register(
        @Body registration: Registration
    ): Call<Auth>

    @POST("auth/password/change/")
    fun changePassword(uid: Int, username: String, newPassword1: String, newPassword2: String)

    @POST("token/")
    fun token(@Body user: User): Call<User>

    @POST("token/verify/")
    fun verifyToken(@Body token: Token): Call<Token>

    @POST("token/refresh/")
    fun refreshToken(@Body user: User): Call<User>
}