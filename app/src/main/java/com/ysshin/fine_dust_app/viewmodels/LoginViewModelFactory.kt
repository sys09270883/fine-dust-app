package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ysshin.fine_dust_app.api.AuthService

class LoginViewModelFactory(private val authService: AuthService): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(authService) as T
}