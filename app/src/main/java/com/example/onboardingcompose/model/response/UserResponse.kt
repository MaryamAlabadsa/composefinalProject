package com.example.onboardingcompose.model.response
import com.example.onboardingcompose.model.response.BaseResponse
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("data"    ) var data    : UserData?    = UserData()
) : BaseResponse()

data class UserData(
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("email"  ) var email  : String? = null,
    @SerializedName("photo"  ) var photo  : String? = null,
    @SerializedName("phone"  ) var phone  : String? = null,
    @SerializedName("active" ) var active : String? = null,
    @SerializedName("token"  ) var token  : String? = null
)

