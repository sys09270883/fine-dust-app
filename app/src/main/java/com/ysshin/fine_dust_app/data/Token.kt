package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName(value = "token") val token: String = ""
)
