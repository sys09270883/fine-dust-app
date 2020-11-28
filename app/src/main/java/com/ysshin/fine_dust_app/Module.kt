package com.ysshin.fine_dust_app

import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.LoginRepository
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.data.RegistrationRepository
import com.ysshin.fine_dust_app.ui.LoginFragment
import com.ysshin.fine_dust_app.ui.RegistrationFragment
import com.ysshin.fine_dust_app.viewmodels.LoginViewModel
import com.ysshin.fine_dust_app.viewmodels.RegistrationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val fragmentModule = module {
    factory { LoginFragment() }
    factory { RegistrationFragment() }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { RegistrationRepository(get()) }
}

val networkModule = module {
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
    
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    single { HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC } }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideAuthService(get()) }
}

val prefModule = module {
    single { PreferenceManager(androidContext()) }
}


