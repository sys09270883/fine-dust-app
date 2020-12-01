package com.ysshin.fine_dust_app.data

import com.ysshin.fine_dust_app.BuildConfig
import com.ysshin.fine_dust_app.api.WeatherService
import retrofit2.Call

class HomeRepository(private val weatherService: WeatherService) {
    fun getDusts(
        serviceKey: String = BuildConfig.SERVICE_KEY,
        numOfRows: Int = 100,
        pageNo: Int = 1,
        doName: String = "경기",
        siName: String = "안양시",
        searchCondition: String = "HOUR",
        returnType: String = "json"
    ): Call<DustResponse> = weatherService.getDusts(
        serviceKey, numOfRows, pageNo, doName, siName, searchCondition, returnType
    )
}