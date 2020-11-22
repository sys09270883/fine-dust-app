package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName(value = "pk") val uid: Int,
    @SerializedName(value = "username") val username: String,
    @SerializedName(value = "first_name") val firstName: String,
    @SerializedName(value = "last_name") val lastName: String,
    @SerializedName(value = "email") val email: String
) {
}