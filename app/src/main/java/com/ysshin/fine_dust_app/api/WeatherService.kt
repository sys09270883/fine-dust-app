package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.DustResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather?")
    fun getDusts(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("sidoName") sidoName: String,
        @Query("searchCondition") searchCondition: String,
        @Query("_returnType") returnType: String
    ): Call<DustResponse>

    companion object {
        private const val BASE_URL = "http://6eced300547c.ngrok.io/"

        fun create(): WeatherService {
            val interceptor = HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}