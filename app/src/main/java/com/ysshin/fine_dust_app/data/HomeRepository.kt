package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.WeatherService

class HomeRepository(private val weatherService: WeatherService) {
    suspend fun getDusts(doName: String = "", siName: String = "") =
        weatherService.getDusts(doName, siName)

    suspend fun getWeatherInformation(lat: Int = 0, lng: Int = 0) =
        weatherService.getWeather(lat, lng)
}