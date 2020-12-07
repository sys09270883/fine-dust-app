package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.DustResponse
import com.ysshin.fine_dust_app.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather/dust?")
    suspend fun getDusts(
        @Query("doName") doName: String,
        @Query("siName") siName: String
    ): DustResponse

    @GET("weather?")
    suspend fun getWeather(
        @Query("lat") lat: Int,
        @Query("lng") lng: Int
    ): WeatherResponse
}