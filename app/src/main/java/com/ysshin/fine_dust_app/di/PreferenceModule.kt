package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.data.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { PreferenceManager(androidContext()) }
}
