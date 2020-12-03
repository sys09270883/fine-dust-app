package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Sky(
    @SerializedName(value = "time") val time: Int,
    @SerializedName(value = "value") val value: String
)
