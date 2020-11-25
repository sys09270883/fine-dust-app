package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.AuthService
import retrofit2.Call

class LoginRepository(private val authService: AuthService) {
    fun login(loginData: LoginData): Call<AuthData> = authService.login(loginData)
}