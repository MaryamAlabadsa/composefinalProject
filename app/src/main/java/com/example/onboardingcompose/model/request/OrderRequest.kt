package com.example.onboardingcompose.model.request
import com.google.gson.annotations.SerializedName


data class OrderRequest(
    @SerializedName("work_id")
    val work_id: Int,
    @SerializedName("details")
    val details: String,
    @SerializedName("details_address")
    val details_address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("lat")
    val lat: Long,
    @SerializedName("long")
    val long: Long,

)