package com.example.onboardingcompose.model.request
import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: Int,

)