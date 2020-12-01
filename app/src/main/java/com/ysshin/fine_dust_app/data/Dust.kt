package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Dust(
    @SerializedName(value = "dataTime")
    val dataTime: String,
    @SerializedName(value = "cityName")
    val cityName: String,
    @SerializedName(value = "so2Value")
    val so2Value: String,
    @SerializedName(value = "coValue")
    val coValue: String,
    @SerializedName(value = "o3Value")
    val o3Value: String,
    @SerializedName(value = "no2Value")
    val no2Value: String,
    @SerializedName(value = "pm10Value")
    val pm10Value: String,
    @SerializedName(value = "pm25Value")
    var pm25Value: String
)
