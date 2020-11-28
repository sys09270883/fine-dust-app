package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.AuthService
import retrofit2.Call

class RegistrationRepository(private val authService: AuthService) {
    fun register(registrationData: RegistrationData): Call<AuthData> = authService.register(registrationData)
}