package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login/")
    fun login(@Body loginData: LoginData): Call<AuthData>

    @POST("auth/logout/")
    fun logout()

    @POST("auth/registration/")
    fun register(
        @Body registrationData: RegistrationData
    ): Call<AuthData>

    @POST("auth/password/change/")
    fun changePassword(uid: Int, username: String, newPassword1: String, newPassword2: String)

    @POST("token/")
    fun token(@Body userData: UserData): Call<UserData>

    @POST("token/verify/")
    fun verifyToken(@Body token: Token): Call<Token>

    @POST("token/refresh/")
    fun refreshToken(@Body userData: UserData): Call<UserData>
}