package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.api.WeatherService
import retrofit2.Call

class HomeRepository(private val weatherService: WeatherService) {
    fun getDusts(
        doName: String = "",
        siName: String = "",
    ): Call<DustResponse> = weatherService.getDusts(
        doName, siName
    )
}