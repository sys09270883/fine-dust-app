package com.ysshin.fine_dust_app.data

import com.google.gson.annotations.SerializedName

data class RegistrationData(
    @SerializedName(value = "username") val username: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password1") val password1: String,
    @SerializedName(value = "password2") val password2: String,
    @SerializedName(value = "first_name") val firstName: String,
    @SerializedName(value = "last_name") val lastName: String
) {
}