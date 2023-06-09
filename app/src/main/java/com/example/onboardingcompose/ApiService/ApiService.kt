package com.example.myallwork.ApiService

import androidx.datastore.preferences.core.Preferences
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.request.LoginRequest
import com.example.onboardingcompose.model.request.OrderRequest
import com.example.onboardingcompose.model.request.RegisterRequest
import com.example.onboardingcompose.model.response.AllWorkResponse
import com.example.onboardingcompose.model.response.OrderResponse
import com.example.onboardingcompose.model.response.UserResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    @POST("auth/login/user")
    suspend fun login(@Body loginRequest: LoginRequest): UserResponse

    @POST("auth/register/user")
    suspend fun register(@Body registerRequest: RegisterRequest): UserResponse

    @GET("all/works")
    suspend fun allWork(): AllWorkResponse

    @POST("create/order")
    suspend fun createOrder(@Body orderRequest: OrderRequest): OrderResponse


    @GET("order/pending/user")
    suspend fun getPendingOrders(): OrderResponse

    @GET("order/un/complete/user")//todo
    suspend fun getUnCompleteOrders(): OrderResponse

    @GET("order/complete/user")
    suspend fun getCompleteOrders(): OrderResponse


    companion object {
        var apiService: ApiService? = null

        fun getInstance(preferenceHelper: SharedViewModel): ApiService {
            if (apiService == null) {
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer ${preferenceHelper.getUserProfile().token}"
                            )
                            .addHeader("Accept", "application/json")
                            .build()
                        chain.proceed(request)
                    }
                    .build()

                apiService = Retrofit.Builder()
                    .baseUrl("https://studentucas.awamr.com/public/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }


    }

}