package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName(value = "username") val username: String = "",
    @SerializedName(value = "password") val password: String = ""
)
