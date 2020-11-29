package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.viewmodels.HomeViewModel
import com.ysshin.fine_dust_app.viewmodels.LoginViewModel
import com.ysshin.fine_dust_app.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}