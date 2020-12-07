package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.AuthService

class RegistrationRepository(
    private val authService: AuthService,
) {
    suspend fun register(registration: Registration) = authService.register(registration)
}