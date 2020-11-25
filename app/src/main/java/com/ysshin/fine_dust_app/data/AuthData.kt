package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName(value = "token") val token: Token = Token(),
    @SerializedName(value = "user") val user: UserData = UserData()
) {
}