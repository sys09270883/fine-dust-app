package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.AuthInfo
import com.ysshin.fine_dust_app.data.RegistrationInfo
import com.ysshin.fine_dust_app.data.UserInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login/")
    fun login(@Body userInfo: UserInfo): Call<AuthInfo>

    @POST("auth/logout/")
    fun logout()

    @POST("auth/registration/")
    fun register(
        @Body registrationInfo: RegistrationInfo
    ): Call<AuthInfo>

    @POST("auth/password/change/")
    fun changePassword(uid: Int, username: String, newPassword1: String, newPassword2: String)

    @POST("token/")
    fun token(@Body userInfo: UserInfo): Call<UserInfo>

    @POST("token/verify/")
    fun verifyToken(@Body token: String): Call<String>

    @POST("token/refresh/")
    fun refreshToken(@Body userInfo: UserInfo): Call<UserInfo>

    companion object {
        private const val BASE_URL = "http://7ee48b470362.ngrok.io/"

        fun create(): AuthService {
            val interceptor = HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthService::class.java)
        }
    }

}