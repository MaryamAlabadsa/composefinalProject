package com.example.myallwork.ViewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myallwork.ApiService.ApiService
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.request.LoginRequest
import com.example.onboardingcompose.model.request.OrderRequest
import com.example.onboardingcompose.model.request.RegisterRequest
import com.example.onboardingcompose.model.response.*
import com.example.onboardingcompose.navigation.Screen
import com.example.onboardingcompose.screen.BottomNavItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ApiServiceModel : ViewModel() {

    var liveAllWorkData: List<AllWorkData> by mutableStateOf(listOf())
    var liveOrderData: List<OrderData> by mutableStateOf(listOf())

    suspend fun getUser(
        loginRequest: LoginRequest,
        context: Context,
        sharedViewModel: SharedViewModel
    ): UserData? {
        return try {
            val apiService = ApiService.getInstance(sharedViewModel)
            val userResponse = apiService.login(loginRequest)
            val userData = userResponse.data

            if (userResponse.success == true) {
                if (userData != null) {
                    userData
                } else {
                    null
                }
            } else {
                Toast.makeText(context, userResponse.message + "", Toast.LENGTH_LONG).show()
                null
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show()
            null
        }
    }


    fun registerUser(
        registerRequest: RegisterRequest,
        sharedViewModel: SharedViewModel,
        navController: NavHostController,
        context: Context,

        ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val userResponse = apiService.register(registerRequest)
                // Handle the response as needed
                val userData = userResponse.data
                if (userResponse.success == true) {
                    if (userData != null) {
                        sharedViewModel.setUserProfile(userData)
                        userData.token?.let { sharedViewModel.setToken(it) }
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                } else {
                    Toast.makeText(context, userResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", userResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun allWorkData(
        sharedViewModel: SharedViewModel,
        context: Context,

        ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val allWorkResponse = apiService.allWork()
                val allWorkData = allWorkResponse.data
                if (allWorkResponse.success == true) {
                    if (allWorkData != null) {
                        liveAllWorkData = allWorkData
                    }
                } else {
                    Toast.makeText(context, allWorkResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", allWorkResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun createOrder(
        sharedViewModel: SharedViewModel,
        context: Context,
        orderRequest: OrderRequest,
        navControllerHome: NavHostController,
        na: NavHostController
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val orderResponse = apiService.createOrder(orderRequest)
                val orderData = orderResponse.data
                if (orderResponse.success == true) {
                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show();
                    na.apply {
                        popBackStack() // Close and finish the current destination
                    }
                    navControllerHome.navigate(BottomNavItem.Service.screen_route)
                    liveOrderData = orderData
                } else {
                    Toast.makeText(context, orderResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", orderResponse.toString())
                }
            } catch (e: Exception) {
                Toast.makeText(context, "11", Toast.LENGTH_LONG).show();

                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
                Log.e("errormmmm", e.message .toString())

            }
        }
    }

    fun pendingOrders(
        sharedViewModel: SharedViewModel,
        context: Context,
   ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val orderResponse = apiService.getPendingOrders()
                val orderData = orderResponse.data
                if (orderResponse.success == true) {
//                    navControllerHome.navigate(BottomNavItem.Service.screen_route)

                    liveOrderData = orderData
                } else {
                    Toast.makeText(context, orderResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", orderResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun unCompleteOrders(
        sharedViewModel: SharedViewModel,
        context: Context,
  ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val orderResponse = apiService.getUnCompleteOrders()
                val orderData = orderResponse.data
                if (orderResponse.success == true) {
//                    navControllerHome.navigate(BottomNavItem.Service.screen_route)

                    liveOrderData = orderData
                } else {
                    Toast.makeText(context, orderResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", orderResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun completeOrders(
        sharedViewModel: SharedViewModel,
        context: Context,
   ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance(sharedViewModel)
                val orderResponse = apiService.getCompleteOrders()
                val orderData = orderResponse.data
                if (orderResponse.success == true) {
//                    navControllerHome.navigate(BottomNavItem.Service.screen_route)

                    liveOrderData = orderData
                } else {
                    Toast.makeText(context, orderResponse.message + "", Toast.LENGTH_LONG).show();
                    Log.e("errormmmm", orderResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

}
