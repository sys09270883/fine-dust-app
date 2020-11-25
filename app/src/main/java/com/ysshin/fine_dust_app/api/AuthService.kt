package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.LoginData
import com.ysshin.fine_dust_app.data.RegistrationData
import com.ysshin.fine_dust_app.data.UserData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun verifyToken(@Body token: AuthData): Call<AuthData>

    @POST("token/refresh/")
    fun refreshToken(@Body userData: UserData): Call<UserData>

    companion object {
        private const val BASE_URL = "https://gvkuhjey1j.execute-api.ap-northeast-2.amazonaws.com/dev/"

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