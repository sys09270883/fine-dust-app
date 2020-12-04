package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Sky(
    @SerializedName(value = "hour") val hour: Int,
    @SerializedName(value = "value") val value: Int
)
