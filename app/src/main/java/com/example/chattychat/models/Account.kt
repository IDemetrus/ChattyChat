package com.example.chattychat.models

import com.google.gson.annotations.SerializedName

class Account(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String
) {
    val user: String? = null
    val token: String? = null
}