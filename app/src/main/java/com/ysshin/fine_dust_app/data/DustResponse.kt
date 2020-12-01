package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class DustResponse(
    @SerializedName(value = "dusts") val dusts: List<Dust>,
    @SerializedName(value = "totalCount") val totalCount: Int,
)
