package com.ysshin.fine_dust_app.di

import com.ysshin.fine_dust_app.BuildConfig
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.api.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    single { HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC } }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideAuthService(get()) }
    single { provideWeatherService(get()) }
}