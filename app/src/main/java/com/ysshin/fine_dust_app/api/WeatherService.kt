package com.ysshin.fine_dust_app.api

import com.ysshin.fine_dust_app.data.DustResponse
import retrofit2.Call
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
}