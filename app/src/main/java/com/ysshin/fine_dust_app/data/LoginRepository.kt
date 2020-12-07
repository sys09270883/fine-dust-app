package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.AuthService

class LoginRepository(private val authService: AuthService) {
    suspend fun login(login: Login) = authService.login(login)

    suspend fun verifyToken(token: String) = authService.verifyToken(Token(token))
}