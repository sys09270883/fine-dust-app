package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.ui.LoginFragment
import com.ysshin.fine_dust_app.ui.RegistrationFragment
import org.koin.dsl.module

val fragmentModule = module {
    factory { LoginFragment() }
    factory { RegistrationFragment() }
}
