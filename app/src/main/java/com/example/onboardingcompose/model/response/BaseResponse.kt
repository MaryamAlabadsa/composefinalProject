package com.example.onboardingcompose.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//import kotlinx.parcelize.Parcelize


//@Parcelize
open class BaseResponse(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("message") var message: String? = null,
)
//    : Parcelable