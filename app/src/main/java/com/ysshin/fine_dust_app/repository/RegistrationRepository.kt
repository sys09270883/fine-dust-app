package com.ysshin.fine_dust_app.repository

import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.RegistrationData
import retrofit2.Call

class RegistrationRepository(private val authService: AuthService) {
    fun register(registrationData: RegistrationData): Call<AuthData> = authService.register(registrationData)
}