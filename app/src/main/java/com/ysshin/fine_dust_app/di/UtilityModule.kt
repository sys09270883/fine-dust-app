package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.utils.LocationUtil
import com.ysshin.fine_dust_app.utils.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilityModule = module {
    single { PreferenceManager(androidContext()) }
    single { LocationUtil(androidContext()) }
}
