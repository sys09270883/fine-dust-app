package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.data.HomeRepository
import com.ysshin.fine_dust_app.data.LoginRepository
import com.ysshin.fine_dust_app.data.RegistrationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { RegistrationRepository(get()) }
    single { HomeRepository() }
}