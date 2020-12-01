package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName(value = "token") val token: String = "",
    @SerializedName(value = "user") val user: User = User()
) {
}