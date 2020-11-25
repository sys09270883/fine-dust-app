package com.ysshin.fine_dust_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ysshin.fine_dust_app.api.AuthService

class RegistrationViewModelFactory(private val authService: AuthService): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = RegistrationViewModel(authService) as T
}