package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName(value = "skyList")
    val skyList: List<Sky>,
    @SerializedName(value = "maxTemperature")
    val maxTemperature: Double,
    @SerializedName(value = "minTemperature")
    val minTemperature: Double
)
